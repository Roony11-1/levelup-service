package com.patitofeliz.levelup_service.controller.ubicacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.ubicacion.Region;
import com.patitofeliz.levelup_service.service.ubicacion.RegionService;

@RestController
@RequestMapping("/api/ubicacion/regiones")
@CrossOrigin(origins = "http://localhost:5173")
public class RegionController 
{
    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Region>> findAll()
    {
        List<Region> regiones = this.regionService.findAll();

        if (regiones.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(regiones);
    }
}
