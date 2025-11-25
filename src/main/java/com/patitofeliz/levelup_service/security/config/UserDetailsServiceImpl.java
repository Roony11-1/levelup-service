package com.patitofeliz.levelup_service.security.config;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.patitofeliz.levelup_service.model.usuario.Usuario;
import com.patitofeliz.levelup_service.repository.usuario.UsuarioRepository;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService 
{
    // Asume que tienes un UserRepository de Spring Data JPA
    @Autowired
    private final UsuarioRepository usuarioRepository; 

    @Override
    public UserDetails loadUserByUsername(String email)
    {
        Usuario usuario = usuarioRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new CustomUserDetails(usuario);
    }
}