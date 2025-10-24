package com.patitofeliz.levelup_service.service.ubicacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.ubicacion.Region;
import com.patitofeliz.levelup_service.repository.ubicacion.RegionRepository;

@Service
public class RegionService 
{
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll()
    {
        return this.regionRepository.findAll();
    }
}
