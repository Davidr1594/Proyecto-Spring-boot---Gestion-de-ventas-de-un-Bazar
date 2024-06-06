package com.proyectointegrador.bazar_tp.dto;

import com.proyectointegrador.bazar_tp.model.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
//DTO para pasar los datos de una venta.
@Getter @Setter
public class VentaDTO {

    private LocalDate fechaVenta;
    private Cliente cliente;

    private List<VentaProductoDTO> listaProductos;

    public VentaDTO(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public VentaDTO(LocalDate fechaVenta, Cliente cliente, List<VentaProductoDTO> listaProductos) {
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.listaProductos = listaProductos;
    }
}

