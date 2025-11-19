package com.patitofeliz.levelup_service.model.gacha;

import com.patitofeliz.levelup_service.model.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Pull 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "banner_id")
    private Banner banner;
    
    // Info de la tirada para reconstruir el objeto
    private String nombre;
    private String clase;
    private String tipo;
    private String rareza;
}