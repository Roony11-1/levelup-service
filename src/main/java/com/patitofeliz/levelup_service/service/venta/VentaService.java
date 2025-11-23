package com.patitofeliz.levelup_service.service.venta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.producto.Producto;
import com.patitofeliz.levelup_service.model.venta.Venta;
import com.patitofeliz.levelup_service.repository.producto.ProductoRepository;
import com.patitofeliz.levelup_service.repository.venta.VentaRepository;

@Service
public class VentaService 
{
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public List<Venta> findAll()
    {
        return ventaRepository.findAll();
    }

    public Venta save(Venta venta) 
    {
        if (venta.getVentaProductos() != null) {
            for (var vp : venta.getVentaProductos()) 
                {

                Producto producto = productoRepository.findById(vp.getProducto().getId())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                vp.setProducto(producto);

                vp.setVenta(venta);
            }
        }

        return ventaRepository.save(venta);
    }
}
