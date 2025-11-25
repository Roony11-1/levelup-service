package com.patitofeliz.levelup_service.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/api/admin")
// Todas las rutas aquí ya requieren ROLE_ADMIN por 
// la configuración en SecurityConfiguration
public class AdminController 
{
    /*@GetMapping("/hello")
    public String sayHelloAdmin() 
    {
        return "Hola, Admin!";
    }
    
    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('admin:read')")
    public String viewDashboard() {
        return "Vista del Dashboard de Admin";
    }*/
}