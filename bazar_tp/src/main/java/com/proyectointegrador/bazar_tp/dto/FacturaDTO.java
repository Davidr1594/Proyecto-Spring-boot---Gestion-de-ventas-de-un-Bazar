package com.proyectointegrador.bazar_tp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
//DTO para poder imprimir los detalles de una venta.
@Getter @Setter
public class FacturaDTO {

    private Long codigo_venta;
    private LocalDate fecha_venta;
    private String dni_cliente;
    private String nombre_cliente;
    private String apellido_cliente;
    private double total_venta;
    private List<Factura_lista_productosDTO> listaProductosFactura;

    public FacturaDTO() {
    }

    public FacturaDTO(Long codigo_venta, LocalDate fecha_venta, String dni_cliente, String nombre_cliente, String apellido_cliente, double total_venta, List<Factura_lista_productosDTO> listaProductosFactura) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.dni_cliente = dni_cliente;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.total_venta = total_venta;
        this.listaProductosFactura = listaProductosFactura;
    }
}
