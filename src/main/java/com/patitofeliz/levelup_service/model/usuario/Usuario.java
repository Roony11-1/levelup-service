package com.patitofeliz.levelup_service.model.usuario;



import java.util.List;

import com.patitofeliz.levelup_service.security.auth.RegisterRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public Usuario getUserFromRequest(RegisterRequest registerRequest)
    {
        return Usuario.builder()
            .nombreUsuario(registerRequest.getNombreUsuario())
            .email(registerRequest.getEmail())
            .contraseña(registerRequest.getContraseña())
            .telefono(registerRequest.getTelefono())
            .comuna(registerRequest.getComuna())
            .region(registerRequest.getRegion())
            .tipo(registerRequest.getTipo())
            .build();
    }
}