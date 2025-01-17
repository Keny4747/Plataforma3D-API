package com.plataforma3d.plataforma3D2024.controller;

import com.plataforma3d.plataforma3D2024.model.Prueba;
import com.plataforma3d.plataforma3D2024.repository.PruebaRepo;
import com.plataforma3d.plataforma3D2024.service.PruebaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prueba")
public class PruebaController {

    private final PruebaService pruebaService;
    private final PruebaRepo pruebaRepo;

    public PruebaController(PruebaService pruebaService, PruebaRepo pruebaRepo) {
        this.pruebaService = pruebaService;
        this.pruebaRepo = pruebaRepo;
    }

    @GetMapping("/list")
    public List<Prueba> listarPruebas() {
        return (List<Prueba>) pruebaRepo.findAll();
    }

    @PostMapping("/new")
    public Prueba crearPrueba(@RequestBody Prueba prueba) {
        return pruebaService.save(prueba);
    }


}
