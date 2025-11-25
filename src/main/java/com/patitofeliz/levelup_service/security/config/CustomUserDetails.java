package com.patitofeliz.levelup_service.security.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.patitofeliz.levelup_service.model.usuario.Usuario;

import lombok.Builder;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@Builder
public class CustomUserDetails implements UserDetails 
{
    private final Usuario usuario;

    public CustomUserDetails(Usuario usuario) 
    {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        // Asumimos que tienes roles en el modelo 'Usuario' como una lista de cadenas
        return usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role)) // Convertimos los roles en authorities
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() 
    {
        return usuario.getContraseña(); // Retorna la contraseña cifrada
    }

    @Override
    public String getUsername() 
    {
        return usuario.getEmail(); // Usamos el email como nombre de usuario
    }

    @Override
    public boolean isAccountNonExpired() 
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() 
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() 
    {
        return true;
    }

    @Override
    public boolean isEnabled() 
    {
        return true; // Si el usuario está habilitado
    }

    public Usuario getUsuario() 
    {
        return usuario; // Puedes agregar métodos personalizados si es necesario
    }
}
