package com.plataforma3d.plataforma3D2024.service;

import com.plataforma3d.plataforma3D2024.dto.EstudianteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEstudianteService {
    EstudianteDTO save(EstudianteDTO estudianteDTO)throws Exception;
    List<EstudianteDTO> readAll()throws Exception;
    EstudianteDTO findById(Integer id)throws Exception;
    void delete(Integer id)throws Exception;

    EstudianteDTO update(Integer id, EstudianteDTO estudianteDTO)throws Exception;

    Page<EstudianteDTO> paginate(Pageable pageable);
}

