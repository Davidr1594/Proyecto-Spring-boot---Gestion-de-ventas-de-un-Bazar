package com.proyectointegrador.bazar_tp.repository;

import com.proyectointegrador.bazar_tp.model.VentaProducto;
import com.proyectointegrador.bazar_tp.model.VentaProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaProductoRepository extends JpaRepository<VentaProducto, VentaProductoId> {
}
