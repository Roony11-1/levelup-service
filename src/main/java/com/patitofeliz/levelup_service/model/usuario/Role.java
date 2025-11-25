package com.patitofeliz.levelup_service.model.usuario;

public enum Role {
    USER,
    ADMIN;
    
    public String getAuthority() 
    {
        return "ROLE_" + name();
    }
}
