package com.patitofeliz.levelup_service.model.gacha;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BannerItem 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String clase;
    private String tipo;
    private String rareza;
    private double probabilidad;
    private boolean activo;
}