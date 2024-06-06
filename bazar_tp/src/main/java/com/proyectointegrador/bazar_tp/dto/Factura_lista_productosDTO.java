package com.proyectointegrador.bazar_tp.dto;

import lombok.Getter;
import lombok.Setter;
//DTO para crear una lista de productos a detalle en FacturaDTO
@Getter @Setter
public class Factura_lista_productosDTO {

    private Long id_producto;
    private String nombre_producto;
    private String marca_producto;
    private int cantidad_producto;
    private double precio_producto;

    public Factura_lista_productosDTO() {
    }

    public Factura_lista_productosDTO(String nombre_producto, String marca_producto, int cantidad_producto, double precio_producto) {
        this.nombre_producto = nombre_producto;
        this.marca_producto = marca_producto;
        this.cantidad_producto = cantidad_producto;
        this.precio_producto = precio_producto;
    }
}
