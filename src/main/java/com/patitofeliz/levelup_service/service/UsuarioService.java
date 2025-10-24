package com.patitofeliz.levelup_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.Response;
import com.patitofeliz.levelup_service.model.ubicacion.Comuna;
import com.patitofeliz.levelup_service.model.usuario.Usuario;
import com.patitofeliz.levelup_service.repository.ubicacion.ComunaRepository;
import com.patitofeliz.levelup_service.repository.usuario.UsuarioRepository;

@Service
public class UsuarioService 
{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ComunaRepository comunaRepository;

    public List<Usuario> findAll()
    {
        return this.usuarioRepository.findAll();
    }

    public Usuario findById(int id)
    {
        return this.usuarioRepository.findById(id).orElse(null);
    }

    public Response<Usuario> save(Usuario usuario) 
    {
        Response<Usuario> response = new Response<>(true, "Usuario registrado", null, null);

        if ((usuario.getEmail() == null) || (usuario.getEmail().trim().isBlank())) 
        {
            response.setMessage("Usuario no registrado: No posee email");
            response.setStatus("409");
            return response;
        }

    if (usuario.getComunaId() != null && usuario.getComunaId() != 0) 
    {
        Comuna comuna = comunaRepository.findById(usuario.getComunaId()).orElse(null);
        if (comuna == null) 
        {
            response.setSuccess(false);
            response.setMessage("Comuna no encontrada");
            response.setStatus("404");
            return response;
        }
        usuario.setComuna(comuna);
    }

        if (this.usuarioRepository.existsByEmail(usuario.getEmail())) 
        {
            response.setMessage("Usuario no registrado: Email duplicado");
            response.setStatus("400");
            return response;
        }

        Usuario usuarioNuevo = this.usuarioRepository.save(usuario);
        response.setEntity(usuarioNuevo);
        response.setStatus("201");

        return response;
    }

    public Response<Usuario> login(String email, String contraseña)
    {
        Response<Usuario> response = new Response<>(true, "Usuario Logeado", "200", null);

        Usuario usuarioLogeado = this.usuarioRepository.findByEmail(email).orElse(null);

        if (usuarioLogeado == null)
        {
            response.setMessage("Error al logear: Correo no existe");
            response.setStatus("404");
            return response;
        }

        if (!usuarioLogeado.getContraseña().trim().equals(contraseña.trim()))
        {
            response.setMessage("Error al logear: Contraseña incorrecta");
            response.setStatus("401");
            return response;
        }

        response.setEntity(usuarioLogeado);

        return response;
    }
}
