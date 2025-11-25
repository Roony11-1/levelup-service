package com.patitofeliz.levelup_service.security.auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticationResponse {
    private String token;
      // Puedes añadir más datos si lo deseas, 
    // como el nombre de usuario o roles
    
}
