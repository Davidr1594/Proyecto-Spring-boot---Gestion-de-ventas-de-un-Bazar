package com.proyectointegrador.bazar_tp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
//DTO para poder visualizar las ventas
@Getter @Setter
public class VentaViewDTO {

    private Long codigo_venta;
    private LocalDate fecha_venta;
    private double total;
    private List<Long> listaProductosIds;
    private Long clienteId;

    public VentaViewDTO() {
    }

    public VentaViewDTO(Long codigo_venta, LocalDate fecha_venta, double total, List<Long> listaProductosIds, Long clienteId) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductosIds = listaProductosIds;
        this.clienteId = clienteId;
    }
}
