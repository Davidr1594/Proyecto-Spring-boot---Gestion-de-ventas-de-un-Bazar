package com.proyectointegrador.bazar_tp.controller;

import com.proyectointegrador.bazar_tp.model.Cliente;
import com.proyectointegrador.bazar_tp.service.IClienteService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//Clase Controller para Cliente
@RestController
public class ClienteController {
    //Inyeccion de dependencia interface ClienteService.
    @Autowired
    IClienteService clienServ;

    //Endpoint para crear un cliente
    @PostMapping("/clientes/crear")
    public String createCliente(@RequestBody Cliente cliente){

        clienServ.saveCliente(cliente);
        return "Cliente Guardado";
    }

    //Endpoint para traer todos los clientes existentes
    @GetMapping("/clientes")
    public List<Cliente> readClientes(){
        return clienServ.readClientes();
    }

    //Endpoint para traer un cliente determinado.
    @GetMapping("/clientes/{id_cliente}")
    public Cliente readOneCliente(@PathVariable Long id_cliente){
        return clienServ.readCliente(id_cliente);
    }

    //Endpoint para eliminar a un cliente
    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public String deleteCliente(@PathVariable Long id_cliente){
        clienServ.deleteCliente(id_cliente);
        return "Cliente eliminado";
    }

    //Endpoint para editar un cliente pasandole un objeto Cliente como parametro.
    @PutMapping("/clientes/editar")
    public String updateCliente(@RequestBody Cliente cliente){
        clienServ.updateCliente(cliente);
        return "Cliente editado";
    }
}
