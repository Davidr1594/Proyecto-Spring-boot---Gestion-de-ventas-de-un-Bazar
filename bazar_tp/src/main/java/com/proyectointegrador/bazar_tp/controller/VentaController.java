package com.proyectointegrador.bazar_tp.controller;

import com.proyectointegrador.bazar_tp.dto.*;
import com.proyectointegrador.bazar_tp.model.*;
import com.proyectointegrador.bazar_tp.repository.VentaProductoRepository;
import com.proyectointegrador.bazar_tp.service.IClienteService;
import com.proyectointegrador.bazar_tp.service.IProductoService;
import com.proyectointegrador.bazar_tp.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Clase Controller para Venta

@RestController
public class VentaController {

    @Autowired
    IVentaService ventaServ;

    @Autowired
    IProductoService prodServ;

    @Autowired
    IClienteService clienteServ;

    @Autowired
    VentaProductoRepository ventaProductoRepository;


    //Endpoint para crear un Venta
    @PostMapping("/ventas/crear")
    public String createVenta(@RequestBody VentaDTO ventaDTO) {

        try {
            // Leer el cliente asociado a la venta usando el ID del cliente del DTO
            Cliente cliente = clienteServ.readCliente(ventaDTO.getCliente().getId_cliente());

            // Si el cliente no existe, lanzar una excepción
            if (cliente == null) {
                throw new RuntimeException("Cliente no encontrado con ID " + ventaDTO.getCliente().getId_cliente());
            }

            // Crear una nueva instancia de Venta y configurar sus atributos
            Venta venta = new Venta();
            venta.setFecha_venta(ventaDTO.getFechaVenta());
            venta.setUnCliente(cliente);
            ventaServ.saveVenta(venta); // Guardar la venta en la base de datos

            double total = 0; // Inicializar el total de la venta
            List<VentaProducto> listaProductos = new ArrayList<>(); // Lista para almacenar los productos de la venta

            // Iterar sobre los productos en la lista del DTO de la venta
            for (VentaProductoDTO vpDTO : ventaDTO.getListaProductos()) {
                // Leer el producto usando el ID del producto del DTO
                Producto producto = prodServ.readProducto(vpDTO.getProductoId());
                if (producto == null) {
                    // Si el producto no existe, lanzar una excepción
                    throw new RuntimeException("Producto con ID " + vpDTO.getProductoId() + " no encontrado");
                }
                System.out.println("Producto encontrado: " + producto.getNombre());

                // Crear una nueva instancia de VentaProducto y configurar sus atributos
                VentaProducto vp = new VentaProducto();
                vp.setVenta(venta);
                vp.setProducto(producto);

                // Verificar si hay suficiente stock del producto
                if (producto.getCantidad_disponible() <= vpDTO.getCantidad()) {
                    throw new RuntimeException("No hay existencia suficiente, queda " + producto.getCantidad_disponible() + " de este producto");
                }
                vp.setCantidad(vpDTO.getCantidad());
                double totalPro = producto.getPrecio() * vpDTO.getCantidad(); // Calcular el total del producto
                vp.setTotalPro(totalPro);

                // Crear y configurar el ID compuesto de VentaProducto
                VentaProductoId vpId = new VentaProductoId();
                vpId.setVentaId(venta.getCodigo_venta());
                vpId.setProductoId(producto.getId_producto());
                vp.setId(vpId);

                listaProductos.add(vp); // Añadir el producto a la lista de productos de la venta
                total += totalPro; // Añadir el total del producto al total de la venta
            }

            // Configurar la lista de productos y el total en la venta
            venta.setListaProductos(listaProductos);
            venta.setTotal(total);

            // Guardar la venta en la base de datos y obtener la venta guardada
            Venta savedVenta = ventaServ.saveVenta(venta);

            // Actualizar el stock de los productos después de la venta
            for (VentaProductoDTO vpDTO : ventaDTO.getListaProductos()) {
                Producto productoUpdate = prodServ.readProducto(vpDTO.getProductoId());
                prodServ.updateProductoStock(productoUpdate.getId_producto(), vpDTO.getCantidad());
            }

            // Guardar cada VentaProducto en la base de datos con la venta guardada
            for (VentaProducto vp : listaProductos) {
                vp.setVenta(savedVenta);
                ventaProductoRepository.save(vp);
            }

        } catch (RuntimeException e) {
            // Manejar excepciones lanzando una nueva excepción con el mensaje original
            throw new RuntimeException(e);
        }

        // Devolver un mensaje indicando que la venta ha sido creada
        return "Venta creada";
    }


    //Endpoint para traer todos las Ventas existentes
    @GetMapping("/ventas")
    public List<VentaViewDTO> readVentas() {
        // Leer todas las ventas usando el servicio de ventas
        List<Venta> listaVentas = ventaServ.readVentas();
        // Lista para almacenar las ventas convertidas a VentaViewDTO
        List<VentaViewDTO> listaVentasVDTO = new ArrayList<>();

        // Iterar sobre cada venta en la lista de ventas
        for (Venta venta : listaVentas) {
            // Crear una nueva instancia de VentaViewDTO y configurar sus atributos
            VentaViewDTO ventaVDTO = new VentaViewDTO();
            ventaVDTO.setCodigo_venta(venta.getCodigo_venta());
            ventaVDTO.setFecha_venta(venta.getFecha_venta());
            ventaVDTO.setTotal(venta.getTotal());
            ventaVDTO.setClienteId(venta.getUnCliente().getId_cliente());

            // Lista para almacenar los IDs de los productos de la venta
            List<Long> productosIds = new ArrayList<>();
            // Iterar sobre cada VentaProducto en la lista de productos de la venta
            for (VentaProducto vp : venta.getListaProductos()) {
                // Añadir el ID del producto a la lista de IDs de productos
                productosIds.add(vp.getProducto().getId_producto());
            }
            // Configurar la lista de IDs de productos en el VentaViewDTO
            ventaVDTO.setListaProductosIds(productosIds);
            // Añadir el VentaViewDTO a la lista de ventas DTO
            listaVentasVDTO.add(ventaVDTO);
        }

        // Devolver la lista de ventas DTO
        return listaVentasVDTO;
    }


    //Endpoint para imprimir una venta a detalle tipo factura
    @GetMapping("/ventas/factura/{id_venta}")
    public FacturaDTO getFactura(@PathVariable Long id_venta) {
        // Obtener la venta por ID
        Venta venta = ventaServ.readVenta(id_venta);

        // Crear el DTO de la factura y establecer sus atributos
        FacturaDTO factuDTO = new FacturaDTO();
        factuDTO.setCodigo_venta(venta.getCodigo_venta());
        factuDTO.setFecha_venta(venta.getFecha_venta());
        factuDTO.setNombre_cliente(venta.getUnCliente().getNombre());
        factuDTO.setApellido_cliente(venta.getUnCliente().getApellido());
        factuDTO.setTotal_venta(venta.getTotal());
        factuDTO.setDni_cliente(venta.getUnCliente().getDni());

        // Lista para almacenar los productos de la factura
        List<Factura_lista_productosDTO> factuProductoList = new ArrayList<>();

        // Iterar sobre los productos de la venta y añadirlos a la lista
        for (VentaProducto vp : venta.getListaProductos()) {
            Factura_lista_productosDTO factuProducto = new Factura_lista_productosDTO();
            factuProducto.setCantidad_producto(vp.getCantidad());
            factuProducto.setMarca_producto(vp.getProducto().getMarca());
            factuProducto.setNombre_producto(vp.getProducto().getNombre());
            factuProducto.setPrecio_producto(vp.getProducto().getPrecio());
            factuProducto.setId_producto(vp.getProducto().getId_producto());
            factuProductoList.add(factuProducto);
        }
        factuDTO.setListaProductosFactura(factuProductoList);

        // Devolver el DTO de la factura
        return factuDTO;
    }

    // Endpoint para traer una Venta determinada.
    @GetMapping("/ventas/{id_venta}")
    public Venta readOneVentas(@PathVariable Long id_venta) {
        // Obtener y devolver la venta por ID
        return ventaServ.readVenta(id_venta);
    }

    // Endpoint para eliminar a una Venta
    @DeleteMapping("/ventas/eliminar/{id_venta}")
    public String deleteVenta(@PathVariable Long id_venta) {
        // Eliminar la venta por ID
        ventaServ.deleteVenta(id_venta);
        // Devolver mensaje de confirmación
        return "Venta eliminada";
    }

    // Endpoint para editar una Venta pasándole un objeto VentaDTO y Id de venta como parámetro.
    @PutMapping("/ventas/editar/{id_venta}")
    public String updateVenta(@PathVariable Long id_venta, @RequestBody VentaDTO ventaDTO) {
        try {
            // Buscar la venta existente por ID
            Venta ventaExistente = ventaServ.readVenta(id_venta);

            if (ventaExistente == null) {
                // Lanzar excepción si la venta no existe
                throw new RuntimeException("Venta no encontrada con ID " + id_venta);
            }

            // Buscar el cliente
            Cliente cliente = clienteServ.readCliente(ventaDTO.getCliente().getId_cliente());
            if (cliente == null) {
                // Lanzar excepción si el cliente no existe
                throw new RuntimeException("Cliente no encontrado con ID " + ventaDTO.getCliente().getId_cliente());
            }

            // Actualizar la venta existente
            ventaExistente.setFecha_venta(ventaDTO.getFechaVenta());
            ventaExistente.setUnCliente(cliente);

            // Limpiar la lista de productos existente
            ventaExistente.getListaProductos().clear();

            double total = 0;
            List<VentaProducto> listaProductos = new ArrayList<>();

            // Iterar sobre los productos del DTO de la venta
            for (VentaProductoDTO vpDTO : ventaDTO.getListaProductos()) {
                Producto producto = prodServ.readProducto(vpDTO.getProductoId());
                if (producto == null) {
                    // Lanzar excepción si el producto no existe
                    throw new RuntimeException("Producto con ID " + vpDTO.getProductoId() + " no encontrado");
                }

                // Crear y configurar el objeto VentaProducto
                VentaProducto vp = new VentaProducto();
                vp.setVenta(ventaExistente);
                vp.setProducto(producto);
                vp.setCantidad(vpDTO.getCantidad());
                double totalPro = producto.getPrecio() * vpDTO.getCantidad();
                vp.setTotalPro(totalPro);

                // Crear y configurar el ID compuesto de VentaProducto
                VentaProductoId vpId = new VentaProductoId();
                vpId.setVentaId(ventaExistente.getCodigo_venta());
                vpId.setProductoId(producto.getId_producto());
                vp.setId(vpId);

                listaProductos.add(vp);
                total += totalPro;
            }

            // Agregar los nuevos productos a la lista de la venta existente
            ventaExistente.getListaProductos().addAll(listaProductos);
            ventaExistente.setTotal(total);

            // Guardar la venta actualizada
            Venta savedVenta = ventaServ.saveVenta(ventaExistente);

            // Guardar los productos vendidos actualizados
            for (VentaProducto vp : listaProductos) {
                vp.setVenta(savedVenta);
                ventaProductoRepository.save(vp);
            }

        } catch (RuntimeException e) {
            // Manejar excepciones lanzando una nueva excepción con el mensaje original
            throw new RuntimeException(e);
        }

        // Devolver mensaje de confirmación
        return "Venta actualizada";
    }

    // Obtener la lista de productos de una determinada venta
    @GetMapping("/ventas/productos/{codigo_venta}")
    public List<Producto> readProductoVenta(@PathVariable Long codigo_venta) {
        // Crear lista para almacenar los productos de la venta
        List<Producto> listaProductosVenta = new ArrayList<>();
        // Obtener la venta por código
        Venta venta = ventaServ.readVenta(codigo_venta);
        // Iterar sobre los productos de la venta y añadirlos a la lista
        for (VentaProducto vp : venta.getListaProductos()) {
            listaProductosVenta.add(vp.getProducto());
        }

        // Devolver la lista de productos de la venta
        return listaProductosVenta;
    }

    // Obtener la sumatoria del monto y también cantidad total de ventas de un determinado día
    @GetMapping("/ventas/fecha/{fecha_venta}")
    public String readVentaDia(@PathVariable String fecha_venta) {
        // Formatear la fecha recibida
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaRecibida = LocalDate.parse(fecha_venta, formatter);

        // Obtener todas las ventas
        List<Venta> listaVentas = ventaServ.readVentas();
        // Lista para almacenar las ventas del día específico
        List<Venta> listaVentasFechas = new ArrayList<>();

        // Iterar sobre las ventas y filtrar por la fecha específica
        for (Venta venta : listaVentas) {
            if (venta.getFecha_venta().isEqual(fechaRecibida)) {
                listaVentasFechas.add(venta);
            }
        }

        // Calcular el monto total de las ventas del día
        double montoTotalVentas = 0;
        for (Venta ventaActual : listaVentasFechas) {
            montoTotalVentas += ventaActual.getTotal();
        }

        // Obtener la cantidad total de ventas del día
        int cantidadVentas = listaVentasFechas.size();

        // Devolver el resultado
        return "Total de ventas: Lps " + montoTotalVentas + "\nHubo " + cantidadVentas + " ventas";
    }

    // Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el apellido del cliente de la venta con el monto más alto de todas
    @GetMapping("/ventas/mayor_venta")
    public VentaMayorDTO readVentaMayo() {
        // Crear el DTO para la venta con mayor monto
        VentaMayorDTO vmDTO = new VentaMayorDTO();
        // Obtener todas las ventas
        List<Venta> listaVentas = ventaServ.readVentas();
        double ventaMayorTotal = 0;

        // Iterar sobre las ventas y encontrar la venta con el mayor monto
        for (Venta venta : listaVentas) {
            if (venta.getTotal() > ventaMayorTotal) {
                ventaMayorTotal = venta.getTotal();
                vmDTO.setCodigo_venta(venta.getCodigo_venta());
                vmDTO.setTotal(venta.getTotal());
                vmDTO.setNombre_cliente(venta.getUnCliente().getNombre());
                vmDTO.setApellido_cliente(venta.getUnCliente().getApellido());
                vmDTO.setCantida_productos(venta.getListaProductos().size());
            }
        }

        // Devolver el DTO de la venta con el mayor monto
        return vmDTO;
    }
}