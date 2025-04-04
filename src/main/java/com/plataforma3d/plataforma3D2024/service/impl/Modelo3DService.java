package com.plataforma3d.plataforma3D2024.service.impl;

import com.plataforma3d.plataforma3D2024.model.Modelo3D;
import com.plataforma3d.plataforma3D2024.repository.Modelo3DRepository;
import com.plataforma3d.plataforma3D2024.service.aws.S3Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Modelo3DService {
    private final Modelo3DRepository modelo3DRepository;
    private final S3Service s3Service;

    public Modelo3DService(Modelo3DRepository modelo3DRepository, S3Service s3Service) {
        this.modelo3DRepository = modelo3DRepository;
        this.s3Service = s3Service;
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

    public void eliminarModelo(Integer id) {
        Optional<Modelo3D> modelo = modelo3DRepository.findById(id);

        if (modelo.isPresent()) {

            if(modelo.get().getUrl() != null ){
                s3Service.deleteFile(modelo.get().getUrl());
            }

            // MYSQL
            modelo3DRepository.deleteById(id);
        } else {
            throw new RuntimeException("Modelo no encontrado");
        }
    }

    public Modelo3D actualizarModelo(Modelo3D modelo) {
        return modelo3DRepository.save(modelo);
    }

}
