package com.patitofeliz.levelup_service.model.fireemblem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Unidad 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public int idPropietario;
    public String nombre;
    public String clase;
    public int nivel;
    public int experiencia;
    public int crePv;
    public int creFue;
    public int creHab;
    public int creVel;
    public int creSue;
    public int creDef;
    public int creRes;
}
