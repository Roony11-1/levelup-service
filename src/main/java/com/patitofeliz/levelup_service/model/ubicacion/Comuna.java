package com.patitofeliz.levelup_service.model.ubicacion;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comuna 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombreComuna;

    @ManyToOne
    @JoinColumn(name = "id_region")
    @JsonIgnore
    private Region region;

}