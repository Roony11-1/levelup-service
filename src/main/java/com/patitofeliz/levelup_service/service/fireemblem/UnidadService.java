package com.patitofeliz.levelup_service.service.fireemblem;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.Response;
import com.patitofeliz.levelup_service.model.fireemblem.Unidad;
import com.patitofeliz.levelup_service.repository.fireemblem.UnidadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnidadService
{
    private final UnidadRepository unidadRepository;

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
        Response<Unidad> response = new Response<>("Unidad registrada", null);

        Unidad unidadNueva = this.unidadRepository.save(unidad);
        response.setData(unidadNueva);

        return response;
    }

    public Response<Unidad> update(Unidad unidad) 
    {
        Response<Unidad> response = new Response<>("Unidad actualizada", null);

        Unidad unidadActualizada = this.unidadRepository.save(unidad);
        response.setData(unidadActualizada);

        return response;
    }
}
