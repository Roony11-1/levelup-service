package com.patitofeliz.levelup_service.controller.ubicacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.ubicacion.Comuna;
import com.patitofeliz.levelup_service.service.ubicacion.ComunaService;

@RestController
@RequestMapping("/api/ubicacion/comunas")
@CrossOrigin(origins = "http://localhost:5173")
public class ComunaController 
{
    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<Comuna>> findAll()
    {
        List<Comuna> comunas = this.comunaService.findAll();

        if (comunas.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(comunas);
    }

    @GetMapping("/findbyregionid/{id}")
    public ResponseEntity<List<Comuna>> findByRegionId(@PathVariable int id) 
    {
        List<Comuna> comunas = comunaService.findByRegionId(id);

        if (comunas.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(comunas);
    }

    @GetMapping("/findbynombre/{nombre}")
    public ResponseEntity<Comuna> findByNombreComuna(@PathVariable String nombreComuna)
    {
        Comuna comuna = this.comunaService.findByNombreComuna(nombreComuna);

        if (comuna == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(comuna);
    }
}
