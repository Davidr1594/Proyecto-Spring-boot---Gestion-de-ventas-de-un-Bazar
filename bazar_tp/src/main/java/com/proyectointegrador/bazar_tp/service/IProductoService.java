package com.proyectointegrador.bazar_tp.service;

import com.proyectointegrador.bazar_tp.model.Cliente;
import com.proyectointegrador.bazar_tp.model.Producto;

import java.util.List;

//Interface para implementa los metodos CRUD que mandaran a llamar a los Repository.
public interface IProductoService {

    //Create
    public void saveProducto(Producto produ);

    //Read All
    public List<Producto> readProductos();

    //Read One
    public Producto readProducto(Long id_produ);

    //Update
    public void updateProducto(Producto produ);

    //Delete
    public void deleteProducto(Long id_produ);

    //Actualizar disponibilidad de producto al hacer una venta
    public void updateProductoStock(long id_producto, int cantidad);
}
