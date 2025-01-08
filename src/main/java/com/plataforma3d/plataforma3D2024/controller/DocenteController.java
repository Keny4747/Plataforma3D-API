package com.plataforma3d.plataforma3D2024.controller;

import com.plataforma3d.plataforma3D2024.dto.DocenteDTO;
import com.plataforma3d.plataforma3D2024.service.IDocenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docente")
public class DocenteController {
    private final IDocenteService docenteService;

    public DocenteController(IDocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping("/list")
    public List<DocenteDTO> docente() throws Exception {
        return docenteService.readAll();
    }

    @PostMapping("/new")
    public DocenteDTO createDocente(@RequestBody DocenteDTO docenteDto) throws Exception {
        return docenteService.save(docenteDto);
    }

    @GetMapping("/{id}")
    public DocenteDTO getDocenteById(@PathVariable Integer id) throws Exception {
        return docenteService.findById(id);
    }

    @PutMapping("/{id}")
    public DocenteDTO updateDocente(@PathVariable Integer id, @RequestBody DocenteDTO docenteDto) throws Exception {
        return docenteService.update(id, docenteDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDocente(@PathVariable Integer id) throws Exception {
        docenteService.delete(id);
    }


}
