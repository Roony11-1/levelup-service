package com.patitofeliz.levelup_service.model.ubicacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "COMUNA")
@Data
public class Comuna 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties("comunas")
    private Region region;
}