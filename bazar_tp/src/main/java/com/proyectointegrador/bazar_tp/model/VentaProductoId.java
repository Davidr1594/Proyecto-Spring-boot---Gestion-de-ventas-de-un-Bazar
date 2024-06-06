package com.proyectointegrador.bazar_tp.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter @Setter
public class VentaProductoId implements Serializable {
    private long ventaId;
    private long productoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VentaProductoId that)) return false;
        return getVentaId() == that.getVentaId() && getProductoId() == that.getProductoId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVentaId(), getProductoId());
    }
}
