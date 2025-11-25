package com.patitofeliz.levelup_service.controller.venta;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.venta.Venta;
import com.patitofeliz.levelup_service.service.venta.VentaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/venta")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class VentaController 
{
    private final VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> findAll() {
        List<Venta> ventas = ventaService.findAll();
        return ResponseEntity.ok(ventas); // aunque esté vacío, devuelve []
    }

    @PostMapping
    public Venta crearVenta(@RequestBody Venta venta) 
    {
        return ventaService.save(venta);
    }
}
