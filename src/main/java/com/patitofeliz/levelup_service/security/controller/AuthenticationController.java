
package com.patitofeliz.levelup_service.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.security.auth.AuthenticationRequest;
import com.patitofeliz.levelup_service.security.auth.AuthenticationResponse;
import com.patitofeliz.levelup_service.security.auth.AuthenticationService;
import com.patitofeliz.levelup_service.security.auth.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) 
    {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")   
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) 
    {
        return ResponseEntity.ok(service.authenticate(request));
    }
}