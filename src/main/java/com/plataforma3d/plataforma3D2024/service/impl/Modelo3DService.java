package com.plataforma3d.plataforma3D2024.service.impl;

import com.plataforma3d.plataforma3D2024.model.Modelo3D;
import com.plataforma3d.plataforma3D2024.repository.Modelo3DRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Modelo3DService {
    private final Modelo3DRepository modelo3DRepository;

    public Modelo3DService(Modelo3DRepository modelo3DRepository) {
        this.modelo3DRepository = modelo3DRepository;
    }

    public List<Modelo3D> obtenerTodosLosModelos() {
        return modelo3DRepository.findAll();
    }

    public Optional<Modelo3D> obtenerPorNombre(String nombre) {
        return modelo3DRepository.findByNombreLike(nombre);
    }

    public Modelo3D guardarModelo(Modelo3D modelo) {
        return modelo3DRepository.save(modelo);
    }


    public Optional<Modelo3D> obtenerPorId(Integer id) {
        return modelo3DRepository.findById(id);
    }



}
