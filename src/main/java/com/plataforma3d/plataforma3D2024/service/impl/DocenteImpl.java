package com.plataforma3d.plataforma3D2024.service.impl;

import com.plataforma3d.plataforma3D2024.dto.DocenteDTO;
import com.plataforma3d.plataforma3D2024.model.Docente;
import com.plataforma3d.plataforma3D2024.repository.DocenteRepo;
import com.plataforma3d.plataforma3D2024.service.IDocenteService;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteImpl implements IDocenteService {

    private final DocenteRepo docenteRepo;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public DocenteImpl(DocenteRepo docenteRepo, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.docenteRepo = docenteRepo;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public DocenteDTO save(DocenteDTO docenteDto) throws Exception {
        Docente docente = mapper.map(docenteDto, Docente.class);
        docente.setPassword(passwordEncoder.encode(docente.getPassword()));
        docenteRepo.save(docente);

        return docenteDto;
    }

    @Override
    public List<DocenteDTO> readAll() throws Exception {

        return docenteRepo
               .findAll()
               .stream().map(docente -> mapper.map(docente, DocenteDTO.class))
               .toList();
    }

    @Override
    public DocenteDTO findById(Integer id) throws Exception {
        return docenteRepo.findById(id).map(docente1 -> mapper.map(docente1,DocenteDTO.class)).orElse(null);
    }

    @Override
    public void delete(Integer id) throws Exception {
        docenteRepo.deleteById(id);
    }

    @Override
    public DocenteDTO update(Integer id, DocenteDTO docenteDto) throws Exception {
        Docente docente = docenteRepo
            .findById(id)
            .orElseThrow(EntityExistsException::new);
        mapper.map(docenteDto, docente);
        docenteRepo.save(docente);

        return  docenteDto;
    }
}
