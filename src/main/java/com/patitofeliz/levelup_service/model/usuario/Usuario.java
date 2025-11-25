package com.patitofeliz.levelup_service.model.usuario;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String comuna;
    private String region;
    
    private String tipo;
    private String profilePhoto;
    
    private List<String> roles;
}