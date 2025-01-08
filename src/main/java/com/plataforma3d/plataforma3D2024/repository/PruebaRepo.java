package com.plataforma3d.plataforma3D2024.repository;

import com.plataforma3d.plataforma3D2024.model.Prueba;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PruebaRepo extends CrudRepository<Prueba, Integer> {
}
