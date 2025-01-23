package com.plataforma3d.plataforma3D2024.repository;

import com.plataforma3d.plataforma3D2024.model.Docente;
import com.plataforma3d.plataforma3D2024.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface DocenteRepo extends JpaRepository<Docente, Integer> {
    Optional<Docente> findByUsername(String username);
}
