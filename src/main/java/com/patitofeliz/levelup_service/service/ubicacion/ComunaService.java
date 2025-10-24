package com.patitofeliz.levelup_service.service.ubicacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.ubicacion.Comuna;
import com.patitofeliz.levelup_service.repository.ubicacion.ComunaRepository;

@Service
public class ComunaService 
{
    @Autowired
    private ComunaRepository comunaRepository;

    public List<Comuna> findAll()
    {
        return this.comunaRepository.findAll();
    }

    public List<Comuna> findByRegionId(int regionId) 
    {
        return this.comunaRepository.findByRegionId(regionId);
    }

    public Comuna findByNombreComuna(String nombreComuna)
    {
        return this.comunaRepository.findByNombreComuna(nombreComuna).orElse(null);
    }
}
