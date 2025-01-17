package com.plataforma3d.plataforma3D2024.service.impl;

import com.plataforma3d.plataforma3D2024.dto.EstudianteDTO;
import com.plataforma3d.plataforma3D2024.exceptions.ModeloNotFoundException;
import com.plataforma3d.plataforma3D2024.model.Estudiante;
import com.plataforma3d.plataforma3D2024.repository.EstudianteRepo;
import com.plataforma3d.plataforma3D2024.service.IEstudianteService;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

;

@Service
public class EstudianteImpl implements IEstudianteService {

    private final EstudianteRepo estudianteRepo;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;


    public EstudianteImpl(EstudianteRepo estudianteRepo, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.estudianteRepo = estudianteRepo;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EstudianteDTO save(EstudianteDTO estudianteDTO) throws Exception {

        Estudiante estudiante = mapper.map(estudianteDTO, Estudiante.class);
        estudiante.setPassword(passwordEncoder.encode(estudiante.getPassword()));
        estudianteRepo.save(estudiante);
        return estudianteDTO;
    }

    @Override
    public List<EstudianteDTO> readAll() throws Exception {

        return estudianteRepo
               .findAll()
               .stream().map(estudiante -> mapper.map(estudiante, EstudianteDTO.class))
               .toList();
    }

    @Override
    public EstudianteDTO findById(Integer id) throws Exception {
        return estudianteRepo.findById(id).map(estudiante -> mapper.map(estudiante,EstudianteDTO.class)).orElse(null);
    }

    @Override
    public void delete(Integer id) throws Exception {
        estudianteRepo.deleteById(id);
    }

    @Override
    public EstudianteDTO update(Integer id, EstudianteDTO estudianteDTO) throws Exception {
        Estudiante estudiante = estudianteRepo
            .findById(id)
            .orElseThrow(EntityExistsException::new);
        mapper.map(estudianteDTO, estudiante);
        estudianteRepo.save(estudiante);

        return  estudianteDTO;
    }
}
