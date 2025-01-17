package com.plataforma3d.plataforma3D2024.repository;

import com.plataforma3d.plataforma3D2024.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepo extends JpaRepository<Estudiante, Integer> {
    Optional<Estudiante> findByUsername(String username);
}
