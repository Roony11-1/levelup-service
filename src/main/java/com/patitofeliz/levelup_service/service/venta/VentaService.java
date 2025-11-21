package com.patitofeliz.levelup_service.service.venta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.venta.Venta;
import com.patitofeliz.levelup_service.repository.venta.VentaRepository;

@Service
public class VentaService 
{
    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> findAll()
    {
        return ventaRepository.findAll();
    }

    public Venta save(Venta venta)
    {
        return ventaRepository.save(venta);
    }
}
