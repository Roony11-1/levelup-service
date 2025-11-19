package com.patitofeliz.levelup_service.model.gacha;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "banner_id")
    private Banner banner;
}