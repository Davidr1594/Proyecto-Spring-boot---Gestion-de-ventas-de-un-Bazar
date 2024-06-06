package com.proyectointegrador.bazar_tp.service;

import com.proyectointegrador.bazar_tp.model.Cliente;

import java.util.List;

//Interface para implementa los metodos CRUD que mandaran a llamar a los Repository.
public interface IClienteService {

    //Create
    public void saveCliente(Cliente cliente);

    //Read All
    public List<Cliente> readClientes();

    //Read One
    public Cliente readCliente(Long id_cliente);

    //Update
    public void updateCliente(Cliente cliente);

    //Delete
    public void deleteCliente(Long id_cliente);

}
