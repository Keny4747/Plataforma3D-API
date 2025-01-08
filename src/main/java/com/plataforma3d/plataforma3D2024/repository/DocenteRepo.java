package com.plataforma3d.plataforma3D2024.repository;

import com.plataforma3d.plataforma3D2024.model.Docente;
import com.plataforma3d.plataforma3D2024.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepo extends JpaRepository<Docente, Integer> {
}
