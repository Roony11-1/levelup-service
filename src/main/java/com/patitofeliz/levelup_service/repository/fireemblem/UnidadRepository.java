package com.patitofeliz.levelup_service.repository.fireemblem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patitofeliz.levelup_service.model.fireemblem.Unidad;

public interface UnidadRepository extends JpaRepository<Unidad, Integer>
{
    List<Unidad> findAllByIdPropietarioNot(int idPropietario);
    List<Unidad> findAllByIdPropietario(int idPropietario);
}
