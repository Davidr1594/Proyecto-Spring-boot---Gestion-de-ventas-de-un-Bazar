package com.proyectointegrador.bazar_tp.service;

import com.proyectointegrador.bazar_tp.model.Producto;
import com.proyectointegrador.bazar_tp.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    IProductoRepository produRepo;
    @Override
    public void saveProducto(Producto produ) {
        produRepo.save(produ);
    }

    @Override
    public List<Producto> readProductos() {

        return produRepo.findAll();
    }

    @Override
    public Producto readProducto(Long id_produ) {

        return produRepo.findById(id_produ).orElse(null);
    }

    @Override
    public void updateProducto(Producto produ) {
        this.saveProducto(produ);
    }

    @Override
    public void deleteProducto(Long id_produ) {
        produRepo.deleteById(id_produ);
    }

    @Override
    public void updateProductoStock(long id_producto, int cantidad) {

        Producto producto = this.readProducto(id_producto);

        producto.setCantidad_disponible(producto.getCantidad_disponible()-cantidad);

        this.saveProducto(producto);
    }
}
