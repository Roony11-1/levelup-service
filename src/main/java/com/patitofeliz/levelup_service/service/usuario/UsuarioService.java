package com.patitofeliz.levelup_service.service.usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.Response;
import com.patitofeliz.levelup_service.model.usuario.Usuario;
import com.patitofeliz.levelup_service.repository.usuario.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService 
{
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public List<Usuario> findAll()
    {
        return this.usuarioRepository.findAll();
    }

    public Usuario findById(int id)
    {
        return this.usuarioRepository.findById(id).orElse(null);
    }

    public Usuario findByEmail(String email)
    {
        return this.usuarioRepository.findByEmail(email).orElse(null);
    }

    public Response<Usuario> update(int id, Usuario usuario)
    {
        Response<Usuario> response = new Response<Usuario>("Usuario actualizado", null);

        Optional<Usuario> existente = this.usuarioRepository.findByEmail(usuario.getEmail());

        if (existente.isPresent() && existente.get().getId() != id) 
        {
            response.setMessage("Usuario no actualizado: Email duplicado");
            return response;
        }

        usuario.setId(id);
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));

        Usuario usuarioNuevo = this.usuarioRepository.save(usuario);

        response.setData(usuarioNuevo);

        return response;
    }


    public Response<Usuario> save(Usuario usuario) 
    {
        Response<Usuario> response = new Response<Usuario>("Usuario registrado",null);

        if ((usuario.getEmail() == null) || (usuario.getEmail().trim().isBlank())) 
        {
            response.setMessage("Usuario no registrado: No posee email");
            return response;
        }

        if (this.usuarioRepository.existsByEmail(usuario.getEmail())) 
        {
            response.setMessage("Usuario no registrado: Email duplicado");
            return response;
        }

        Usuario usuarioNuevo = this.usuarioRepository.save(usuario);
        response.setData(usuarioNuevo);

        return response;
    }

    public Response<Usuario> login(String email, String contraseña)
    {
        Response<Usuario> response = new Response<>("Usuario Logeado", null);

        Usuario usuarioLogeado = this.usuarioRepository.findByEmail(email).orElse(null);

        if (usuarioLogeado == null)
        {
            response.setMessage("Error al logear: Correo no existe");
            return response;
        }

        if (!usuarioLogeado.getContraseña().trim().equals(contraseña.trim()))
        {
            response.setMessage("Error al logear: Contraseña incorrecta");
            return response;
        }

        response.setData(usuarioLogeado);

        return response;
    }

    public void deleteById(int id)
    {
        usuarioRepository.deleteById(id);
    }
}
