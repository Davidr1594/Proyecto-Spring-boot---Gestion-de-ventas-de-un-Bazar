package com.proyectointegrador.bazar_tp.service;

import com.proyectointegrador.bazar_tp.model.Cliente;
import com.proyectointegrador.bazar_tp.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    IClienteRepository clienRepo;
    @Override
    public void saveCliente(Cliente cliente) {

        clienRepo.save(cliente);

    }

    @Override
    public List<Cliente> readClientes() {

        return clienRepo.findAll();
    }

    @Override
    public Cliente readCliente(Long id_cliente) {

        return clienRepo.findById(id_cliente).orElse(null);
    }

    @Override
    public void updateCliente(Cliente cliente) {
        this.saveCliente(cliente);
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        clienRepo.deleteById(id_cliente);
    }
}
