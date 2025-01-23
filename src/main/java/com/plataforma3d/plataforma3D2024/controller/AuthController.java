package com.plataforma3d.plataforma3D2024.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> login(Authentication authentication) {
        Map<String, String> response = new HashMap<>();
        response.put("username", authentication.getName());
        response.put("message", "Login successful");
        return ResponseEntity.ok(response);
    }

}
