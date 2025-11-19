package com.patitofeliz.levelup_service.model.dto;

import com.patitofeliz.levelup_service.model.gacha.Pull;

import lombok.Data;

@Data
public class PullResponseDTO 
{
    private int pullId;
    private int usuarioId;
    private int bannerId;
    private String nombre;
    private String clase;
    private String tipo;
    private String rareza;

    public PullResponseDTO(Pull pull) 
    {
        this.pullId = pull.getId();
        this.usuarioId = pull.getUsuario().getId();
        this.bannerId = pull.getBanner().getId();
        this.nombre = pull.getNombre();
        this.clase = pull.getClase();
        this.tipo = pull.getTipo();
        this.rareza = pull.getRareza();
    }
}