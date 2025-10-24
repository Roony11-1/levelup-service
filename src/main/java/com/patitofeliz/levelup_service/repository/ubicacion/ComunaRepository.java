package com.patitofeliz.levelup_service.repository.ubicacion;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patitofeliz.levelup_service.model.ubicacion.Comuna;

import java.util.List;
import java.util.Optional;



public interface ComunaRepository extends JpaRepository<Comuna, Integer>
{
    List<Comuna> findByRegionId(int id);
    Optional<Comuna> findByNombreComuna(String nombreComuna);
}
