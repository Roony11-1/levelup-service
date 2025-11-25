package com.patitofeliz.levelup_service.security.auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest 
{
    private int id;

    private String nombreUsuario;
    private String email;
    private String contraseña;
    private String telefono;

    private String comuna;
    private String region;
    
    private String tipo;
}
