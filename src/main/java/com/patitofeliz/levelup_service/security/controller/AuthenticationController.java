
package com.patitofeliz.levelup_service.security.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.Response;
import com.patitofeliz.levelup_service.model.dto.AuthResponseDTO;
import com.patitofeliz.levelup_service.model.usuario.Usuario;
import com.patitofeliz.levelup_service.security.auth.AuthenticationRequest;
import com.patitofeliz.levelup_service.security.auth.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthenticationController 
{
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Response<AuthResponseDTO>> register(@RequestBody Usuario usuario) 
    {
        return ResponseEntity.ok(authenticationService.register(usuario));
    }

    @PostMapping("/authenticate")   
    public ResponseEntity<Response<AuthResponseDTO>> authenticate(@RequestBody AuthenticationRequest request) 
    {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Usuario> findProfile(@PathVariable int id, @RequestHeader("Authorization") String token)
    {
        Usuario usuario = authenticationService.findProfile(id, token);

        if (usuario == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuario);
    }
}