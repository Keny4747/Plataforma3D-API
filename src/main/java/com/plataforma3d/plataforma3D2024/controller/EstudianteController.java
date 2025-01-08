package com.plataforma3d.plataforma3D2024.controller;

import com.plataforma3d.plataforma3D2024.dto.DocenteDTO;
import com.plataforma3d.plataforma3D2024.dto.EstudianteDTO;
import com.plataforma3d.plataforma3D2024.service.IDocenteService;
import com.plataforma3d.plataforma3D2024.service.IEstudianteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    private final IEstudianteService estudianteService;

    public EstudianteController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/list")
    public List<EstudianteDTO> docente() throws Exception {
        return estudianteService.readAll();
    }

    @PostMapping("/new")
    public EstudianteDTO createDocente(@RequestBody EstudianteDTO estudianteDTO) throws Exception {
        return estudianteService.save(estudianteDTO);
    }

    @GetMapping("/{id}")
    public EstudianteDTO getDocenteById(@PathVariable Integer id) throws Exception {
        return estudianteService.findById(id);
    }

    @PutMapping("/{id}")
    public EstudianteDTO updateDocente(@PathVariable Integer id, @RequestBody EstudianteDTO estudianteDTO) throws Exception {
        return estudianteService.update(id, estudianteDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDocente(@PathVariable Integer id) throws Exception {
        estudianteService.delete(id);
    }


}
