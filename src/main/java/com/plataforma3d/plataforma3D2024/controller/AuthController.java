package com.plataforma3d.plataforma3D2024.controller;

import com.plataforma3d.plataforma3D2024.model.Docente;
import com.plataforma3d.plataforma3D2024.model.Estudiante;
import com.plataforma3d.plataforma3D2024.repository.DocenteRepo;
import com.plataforma3d.plataforma3D2024.repository.EstudianteRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Slf4j
@RestController
public class AuthController {
    private final EstudianteRepo estudianteRepo;
    private final DocenteRepo docenteRepo;


    public AuthController(EstudianteRepo estudianteRepo, DocenteRepo docenteRepo) {
        this.estudianteRepo = estudianteRepo;
        this.docenteRepo = docenteRepo;
    }
    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> login(Authentication authentication) {
        String username = authentication.getName();
        String fullName = null;

        // Verificar si el usuario es un estudiante
        Optional<Estudiante> estudiante = estudianteRepo.findByUsername(username);
        if (estudiante.isPresent()) {
            fullName = estudiante.get().getNombre() + " " + estudiante.get().getApellido(); // Obtener nombre completo del estudiante
        } else {
            // Verificar si el usuario es un docente
            Optional<Docente> docente = docenteRepo.findByUsername(username);
            if (docente.isPresent()) {
                fullName = docente.get().getNombre() + " " + docente.get().getApellido(); // Obtener nombre completo del docente
            }
        }


        log.info("Nombre completo: " + fullName);
        Map<String, String> response = new HashMap<>();
        response.put("username", username);
        response.put("message", "Login successful");
        if (fullName != null) {
            response.put("fullName", fullName);
        }

        return ResponseEntity.ok(response);
    }
}
