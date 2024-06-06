package com.proyectointegrador.bazar_tp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//creacion de clase Producto con su respectivo mapeo para relacionar con DB
@Getter @Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_producto;
    private String nombre;
    private String marca;
    private double precio;
    private int cantidad_disponible;

    @JsonIgnore
    @OneToMany(mappedBy = "producto")
    private List<VentaProducto> ventaProductos = new ArrayList<>();
    public Producto() {
    }

    public Producto(Long id_producto, String nombre, String marca, double precio, int cantidad_disponible) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.cantidad_disponible = cantidad_disponible;
    }
}
