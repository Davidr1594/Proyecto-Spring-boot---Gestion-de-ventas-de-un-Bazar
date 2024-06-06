package com.proyectointegrador.bazar_tp.controller;

import com.proyectointegrador.bazar_tp.dto.VentaProductoDTO;
import com.proyectointegrador.bazar_tp.model.Producto;
import com.proyectointegrador.bazar_tp.model.Venta;
import com.proyectointegrador.bazar_tp.model.VentaProducto;
import com.proyectointegrador.bazar_tp.service.IProductoService;
import com.proyectointegrador.bazar_tp.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//Clase Controller para Producto

@RestController
public class ProductoController {

    //Inyeccion de dependencia interface ClienteService.
    @Autowired
    IProductoService produServ;

    @Autowired
    IVentaService ventaServ;

    @PostMapping("/productos/crear")
    public String createProducto(@RequestBody Producto produ) {
        produServ.saveProducto(produ); // Llama al servicio para guardar el producto
        return "Producto guardado";
    }

    // Endpoint para traer todos los Productos existentes
    @GetMapping("/productos")
    public List<Producto> readProductos() {
        return produServ.readProductos(); // Retorna una lista de todos los productos
    }

    // Endpoint para traer un Producto determinado.
    @GetMapping("/productos/{id_produ}")
    public Producto readOneProducto(@PathVariable Long id_produ) {
        return produServ.readProducto(id_produ); // Retorna un producto específico buscado por su ID
    }

    // Endpoint para eliminar un Producto
    @DeleteMapping("/productos/eliminar/{id_produ}")
    public String deleteProducto(@PathVariable Long id_produ) {
        produServ.deleteProducto(id_produ); // Llama al servicio para eliminar un producto por su ID
        return "Producto eliminado";
    }

    // Endpoint para editar un Producto pasándole un objeto Producto como parámetro.
    @PutMapping("/productos/editar/{id_producto}")
    public String updateProducto(@PathVariable Long id_producto, @RequestBody Producto produ) {
        Producto produUpdate = produServ.readProducto(id_producto); // Busca el producto a actualizar por su ID usando los nuevos dato recibidos.
        produUpdate.setNombre(produ.getNombre());
        produUpdate.setMarca(produ.getMarca());
        produUpdate.setPrecio(produ.getPrecio());
        produUpdate.setCantidad_disponible(produ.getCantidad_disponible());

        produServ.updateProducto(produUpdate); // Llama al servicio para actualizar el producto
        return "Producto editado";
    }

    // Obtener todos los productos cuya cantidad_disponible sea menor a 5
    @GetMapping("/productos/falta_stock")
    public List<Producto> readProdctosStockLeft() {
        List<Producto> listaProductos = produServ.readProductos(); // Obtiene todos los productos
        List<Producto> listaProductosStock = new ArrayList<>(); // Crea una lista para almacenar los productos con stock bajo
        for (Producto produ : listaProductos) { // Itera sobre todos los productos
            if (produ.getCantidad_disponible() < 5) { // Verifica si la cantidad disponible es menor a 5
                listaProductosStock.add(produ); // Agrega el producto a la lista de productos con stock bajo
            }
        }

        return listaProductosStock;
    }
}