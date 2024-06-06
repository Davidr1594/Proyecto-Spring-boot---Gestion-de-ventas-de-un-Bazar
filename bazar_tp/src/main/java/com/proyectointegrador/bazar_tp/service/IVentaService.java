package com.proyectointegrador.bazar_tp.service;

import com.proyectointegrador.bazar_tp.model.Venta;

import java.util.List;

//Interface para implementa los metodos CRUD que mandaran a llamar a los Repository.
public interface IVentaService {

    //Create
    public Venta saveVenta(Venta produ);

    //Read All
    public List<Venta> readVentas();

    //Read One
    public Venta readVenta(Long id_venta);

    //Update
    public void updateVenta(Venta venta);

    //Delete
    public void deleteVenta(Long id_venta);
}
