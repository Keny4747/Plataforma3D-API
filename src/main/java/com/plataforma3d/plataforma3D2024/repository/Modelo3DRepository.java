package com.plataforma3d.plataforma3D2024.repository;

import com.plataforma3d.plataforma3D2024.model.Modelo3D;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Modelo3DRepository extends JpaRepository<Modelo3D, Integer> {
    Optional<Modelo3D> findByNombreLike(String nombre);
}
