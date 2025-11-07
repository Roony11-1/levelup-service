package com.patitofeliz.levelup_service.service.fireemblem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.Response;
import com.patitofeliz.levelup_service.model.fireemblem.Unidad;
import com.patitofeliz.levelup_service.repository.fireemblem.UnidadRepository;

@Service
public class UnidadService
{
    @Autowired
    private UnidadRepository unidadRepository;

    public List<Unidad> findAll()
    {
        return this.unidadRepository.findAll();
    }

    public List<Unidad> findAllByIdPropietarioNot(int idPropietario)
    {
        return this.unidadRepository.findAllByIdPropietarioNot(idPropietario);
    }

    public List<Unidad> findAllByIdPropietario(int idPropietario)
    {
        return this.unidadRepository.findAllByIdPropietario(idPropietario);
    }

    public Unidad findById(int id)
    {
        return this.unidadRepository.findById(id).orElse(null);
    }

    public Response<Unidad> save(Unidad unidad) 
    {
        Response<Unidad> response = new Response<>(true, "Unidad registrada", null, null);

        Unidad unidadNueva = this.unidadRepository.save(unidad);
        response.setEntity(unidadNueva);
        response.setStatus("201");

        return response;
    }
}
