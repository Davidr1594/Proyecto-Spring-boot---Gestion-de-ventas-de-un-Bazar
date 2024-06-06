package com.proyectointegrador.bazar_tp.repository;

import com.proyectointegrador.bazar_tp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repositorio que extiende las funciones de Jpa para hacer CRUD con la bd.
@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

}
