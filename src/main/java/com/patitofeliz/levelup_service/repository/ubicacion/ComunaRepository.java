package com.patitofeliz.levelup_service.repository.ubicacion;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patitofeliz.levelup_service.model.ubicacion.Comuna;

public interface ComunaRepository extends JpaRepository<Comuna, Integer>
{
    
}
