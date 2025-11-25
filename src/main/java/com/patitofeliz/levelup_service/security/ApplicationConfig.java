package com.patitofeliz.levelup_service.security;

// configura la aplicacion relacionada con seguridad y autentificacion

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig 
{
    private final UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() 
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // Define qué servicio usar para cargar detalles del usuario
        authProvider.setUserDetailsService(userDetailsService); 
        // Define qué codificador usar para verificar contraseñas
        authProvider.setPasswordEncoder(passwordEncoder()); 
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) 
        throws Exception {
            // Expone el AuthenticationManager, necesario en AuthenticationService
            return config.getAuthenticationManager();}

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Utiliza BCrypt para un cifrado seguro de contraseñas
        return new BCryptPasswordEncoder();}
}