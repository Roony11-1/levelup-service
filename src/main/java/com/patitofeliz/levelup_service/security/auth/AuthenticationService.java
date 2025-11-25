package com.patitofeliz.levelup_service.security.auth;
import com.patitofeliz.levelup_service.model.Response;
import com.patitofeliz.levelup_service.model.dto.AuthResponseDTO;
import com.patitofeliz.levelup_service.model.usuario.Usuario;
import com.patitofeliz.levelup_service.security.config.CustomUserDetails;
import com.patitofeliz.levelup_service.security.jwt.JwtService;
import com.patitofeliz.levelup_service.service.usuario.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthenticationService 
{
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Response<AuthResponseDTO> register(Usuario usuario) 
    {
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));

        Response<Usuario> respuestaSignIn = usuarioService.save(usuario);

        CustomUserDetails details = CustomUserDetails.builder().usuario(respuestaSignIn.getData()).build();

        String jwtToken = jwtService.generateToken(details);

        AuthResponseDTO authResponse = new AuthResponseDTO(respuestaSignIn.getData().getId(), jwtToken);

        return Response.<AuthResponseDTO>builder()
            .message(respuestaSignIn.getMessage())
            .data(authResponse)
            .build();
    }

    public Response<AuthResponseDTO> authenticate(AuthenticationRequest request) 
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getContraseña()));
        
        Usuario usuarioLogin = usuarioService.findByEmail(request.getEmail());
        
        if (usuarioLogin == null)
            new RuntimeException("Usuario no encontrado");

        CustomUserDetails details = CustomUserDetails.builder().usuario(usuarioLogin).build();

        String jwtToken = jwtService.generateToken(details);

        AuthResponseDTO authResponse = new AuthResponseDTO(usuarioLogin.getId(), jwtToken);

        return Response.<AuthResponseDTO>builder()
                .message("Logeado")
                .data(authResponse)
                .build();
    }

    public Usuario findProfile(int id, String token)
    {
        Usuario usuario = usuarioService.findById(id);
        usuario.setContraseña("");

        /*CustomUserDetails details = CustomUserDetails.builder().usuario(usuario).build();

        if (!jwtService.isTokenValid(token, details))
            throw new RuntimeException("Token inválido");*/
        return usuario;
    }
}
    

