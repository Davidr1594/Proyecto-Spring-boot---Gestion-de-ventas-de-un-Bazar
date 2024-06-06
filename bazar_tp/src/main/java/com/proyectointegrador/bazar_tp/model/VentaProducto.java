package com.proyectointegrador.bazar_tp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter @Setter
public class VentaProducto {

    @EmbeddedId
    private VentaProductoId id;

    @JsonBackReference
    @ManyToOne
    @MapsId("ventaId")
    @JoinColumn(name="codigo_venta")
    private Venta venta;

    @JsonManagedReference
    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "id_producto")
    private Producto producto;

    private int cantidad;
    private double totalPro;
}


