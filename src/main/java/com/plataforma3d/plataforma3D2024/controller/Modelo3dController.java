package com.plataforma3d.plataforma3D2024.controller;

import com.plataforma3d.plataforma3D2024.model.Modelo3D;
import com.plataforma3d.plataforma3D2024.service.impl.Modelo3DService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modelos")
public class Modelo3dController {
    @Autowired
    private Modelo3DService modelo3DService;

    @GetMapping
    public List<Modelo3D> listarModelos() {
        return modelo3DService.obtenerTodosLosModelos();
    }
    @GetMapping("/{nombre}")
    public Optional<Modelo3D> buscarPorNombre(@PathVariable String nombre) {
        return modelo3DService.obtenerPorNombre(nombre);
    }
    @PostMapping
    public Modelo3D subirModelo(@RequestBody Modelo3D modelo) {
        return modelo3DService.guardarModelo(modelo);
    }

    @GetMapping("/detalle/{id}")
    public Optional<Modelo3D> buscarPorId(@PathVariable Integer id) {
        return modelo3DService.obtenerPorId(id);
    }
}
