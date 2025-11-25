package com.patitofeliz.levelup_service.security.auth;
import com.patitofeliz.levelup_service.model.usuario.Usuario;
import com.patitofeliz.levelup_service.repository.usuario.UsuarioRepository;
import com.patitofeliz.levelup_service.security.config.CustomUserDetails;
import com.patitofeliz.levelup_service.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
     // Método para manejar el registro de nuevos usuarios (
    // opcional, pero incluido para completitud)
    public AuthenticationResponse register(RegisterRequest request) 
    {
        Usuario usuario = new Usuario().getUserFromRequest(request);
        // Codifica la pass
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        usuario.setRoles(new ArrayList<String>());

        var userSaved = repository.save(usuario);

        var details = CustomUserDetails.builder().usuario(userSaved).build();
        
        var jwtToken = jwtService.generateToken(details);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
     // Método para manejar el inicio de sesión y generar el token
     public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Usa AuthenticationManager para validar las credenciales.
        // Si las credenciales son inválidas, lanzará una excepción AuthenticationException.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getContraseña()));
        
        // Si llega aquí, la autenticación fue exitosa. Buscamos al usuario para generar el token.
        var user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            var details = CustomUserDetails.builder().usuario(user).build();

        var jwtToken = jwtService.generateToken(details);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
    

