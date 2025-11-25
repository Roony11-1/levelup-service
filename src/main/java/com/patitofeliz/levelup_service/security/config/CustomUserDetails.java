package com.patitofeliz.levelup_service.security.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.patitofeliz.levelup_service.model.usuario.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
public class CustomUserDetails implements UserDetails 
{
    private final Usuario usuario;

    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        return List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getTipo().toUpperCase()));
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
