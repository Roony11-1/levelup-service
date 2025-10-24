package com.patitofeliz.levelup_service.model.usuario;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.patitofeliz.levelup_service.model.ubicacion.Comuna;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Usuario 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombreUsuario;
    private String email;
    private String contraseña;
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "id_comuna")
    private Comuna comuna;

    private String tipo;
    private String profilePhoto;
}

