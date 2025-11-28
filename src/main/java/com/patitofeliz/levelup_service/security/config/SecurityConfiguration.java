package com.patitofeliz.levelup_service.security.config;

import lombok.RequiredArgsConstructor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.patitofeliz.levelup_service.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true) // Habilita @PreAuthorize y @Secured
public class SecurityConfiguration 
{
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
    {
        http
            .csrf(AbstractHttpConfigurer::disable) // Desactiva CSRF para APIs
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas (para autenticación) solo de / api/v1/auth/
                .requestMatchers("/api/auth/**").permitAll()
                // Permitir todo el trafico en la parte de movil (no quiero implementar jwt en el telefono)
                .requestMatchers("/api/movil/**").permitAll()
                .requestMatchers("/api/usuarios/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                // Usuario
                .requestMatchers(HttpMethod.GET, "/api/usuarios/id/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/usuarios/**").permitAll()
                .requestMatchers("/api/usuarios/**").hasAuthority("ROLE_ADMIN")
                // Swagger
                .requestMatchers("/doc/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                // Proeducto
                .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/productos/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/productos/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/productos/**").hasAuthority("ROLE_ADMIN")
                // Blogs
                .requestMatchers(HttpMethod.GET, "/api/blog/**").permitAll()
                //Ventas
                .requestMatchers(HttpMethod.GET, "/api/venta/**").hasAuthority("ROLE_ADMIN")
                // Otras rutas protegidas por roles globales (opcional, se usará @PreAuthorize en el controlador)
                .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Gestión de sesión sin estado (JWT)
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)  // Añade el filtro JWT
            .cors();

        return http.build();
    }
}