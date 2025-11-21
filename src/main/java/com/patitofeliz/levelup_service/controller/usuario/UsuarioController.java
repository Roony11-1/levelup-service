package com.patitofeliz.levelup_service.controller.usuario;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.Response;
import com.patitofeliz.levelup_service.model.usuario.Usuario;
import com.patitofeliz.levelup_service.service.usuario.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController 
{
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll()
    {
        List<Usuario> usuarios = this.usuarioService.findAll();

        if (usuarios.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable("id") int id)
    {
        Usuario usuario = this.usuarioService.findById(id);

        if (usuario == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> findByEmail(@PathVariable("email") String email)
    {
        Usuario usuario = this.usuarioService.findByEmail(email);

        if (usuario == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Response<Usuario>> save(@RequestBody Usuario usuario) 
    {
        Response<Usuario> response = this.usuarioService.save(usuario);

        if (!response.isSuccess()) 
        {
            if (response.getMessage().contains("Email duplicado"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            else 
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Usuario>> update(@PathVariable int id, @RequestBody Usuario usuario)
    {
        usuario.setId(id);
        Response<Usuario> response = this.usuarioService.update(id, usuario);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response<Usuario>> login(@RequestBody Map<String, String> loginData)
    {
        String email = loginData.get("email");
        String contraseña = loginData.get("contraseña");
        Response<Usuario> response = this.usuarioService.login(email, contraseña);

        if (!response.isSuccess()) 
        {
            if (response.getMessage().contains("vacíos"))
                return ResponseEntity.badRequest().body(response);
            else if (response.getMessage().contains("no encontrado")) 
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            else if (response.getMessage().contains("incorrecta"))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) 
    {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
