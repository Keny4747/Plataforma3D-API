package com.plataforma3d.plataforma3D2024.service;


import com.plataforma3d.plataforma3D2024.dto.DocenteDTO;

import java.util.List;

public interface IDocenteService {
    DocenteDTO save(DocenteDTO docenteDto)throws Exception;
    List<DocenteDTO> readAll()throws Exception;
    DocenteDTO findById(Integer id)throws Exception;
    void delete(Integer id)throws Exception;

    DocenteDTO update(Integer id, DocenteDTO docenteDto)throws Exception;
}
