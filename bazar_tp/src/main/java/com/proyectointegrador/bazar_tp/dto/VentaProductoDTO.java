package com.proyectointegrador.bazar_tp.dto;

import lombok.Getter;
import lombok.Setter;
//DTO para que VentaDTO reciba una lista de productos con la cantidad de productos vendidos
@Getter @Setter
public class VentaProductoDTO {
    private long productoId;
    private int cantidad;


    public VentaProductoDTO() {


    }

    public VentaProductoDTO(long productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }
}
