package com.proyectointegrador.bazar_tp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaMayorDTO {
    private Long codigo_venta;
    private double total;
    private int cantida_productos;
    private String nombre_cliente;
    private String apellido_cliente;

    public VentaMayorDTO() {
    }

    public VentaMayorDTO(Long codigo_venta, double total, int cantida_productos, String nombre_cliente, String apellido_cliente) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantida_productos = cantida_productos;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
    }
}
