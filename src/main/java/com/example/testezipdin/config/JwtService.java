package com.example.testezipdin.config;

import org.springframework.stereotype.Component;

@Component
public class JwtService {
    public String extractUsername(String token) {
        // Mock: retorna usuário fixo
        return "usuario.jwt";
    }
}
