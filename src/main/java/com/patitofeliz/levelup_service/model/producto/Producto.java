package com.patitofeliz.levelup_service.model.producto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Producto 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String codigo;
    private String nombre;
    private String marca;
    private String descripcion;
    private String categoria;
    private long precio;
    private int cantidad;
    private String imagen;
    private boolean destacado;
    private float oferta;
}
