package com.patitofeliz.levelup_service.model.usuario;

import org.antlr.v4.runtime.misc.NotNull;

import com.patitofeliz.levelup_service.model.ubicacion.Comuna;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "USUARIO")
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

    @Transient
    private Integer comunaId;

    @ManyToOne
    @JoinColumn(name = "comuna_id")
    private Comuna comuna;

    private String tipo;
    private String profilePhoto;
}

