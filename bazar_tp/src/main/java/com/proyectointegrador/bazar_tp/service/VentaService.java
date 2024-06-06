package com.proyectointegrador.bazar_tp.service;

import com.proyectointegrador.bazar_tp.model.Venta;
import com.proyectointegrador.bazar_tp.repository.IVentaRepository;
import com.proyectointegrador.bazar_tp.repository.VentaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    IVentaRepository ventaRepo;
    @Autowired
    private VentaProductoRepository ventaProductoRepository;
    @Override
    public Venta saveVenta(Venta produ) {
        ventaRepo.save(produ);
        return produ;
    }

    @Override
    public List<Venta> readVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta readVenta(Long id_venta) {

        return ventaRepo.findById(id_venta).orElse(null);
    }

    @Override
    public void updateVenta(Venta venta) {
        this.saveVenta(venta);
    }

    @Override
    public void deleteVenta(Long id_venta) {
        ventaRepo.deleteById(id_venta);
    }
}
