package com.plataforma3d.plataforma3D2024.service;

import com.plataforma3d.plataforma3D2024.model.Prueba;
import com.plataforma3d.plataforma3D2024.repository.PruebaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PruebaService {

    private final PruebaRepo pruebaRepo;

    public PruebaService(PruebaRepo pruebaRepo) {
        this.pruebaRepo = pruebaRepo;
    }

    public Prueba save(Prueba prueba) {
        return pruebaRepo.save(prueba);
    }

    public void delete(Integer id) {
        pruebaRepo.deleteById(id);
    }

    public List<Prueba> readAll() {
       return (List<Prueba>) pruebaRepo.findAll();
    }

    public Prueba findById(Integer id) {
       return pruebaRepo.findById(id).orElse(null);
    }

}
