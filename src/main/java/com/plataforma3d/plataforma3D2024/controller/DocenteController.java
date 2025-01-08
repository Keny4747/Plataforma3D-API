package com.plataforma3d.plataforma3D2024.controller;

import com.plataforma3d.plataforma3D2024.dto.DocenteDTO;
import com.plataforma3d.plataforma3D2024.service.IDocenteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DocenteDTO> createDocente(@Valid @RequestBody DocenteDTO docenteDto) throws Exception {
        return new ResponseEntity<>(docenteService.save(docenteDto), HttpStatus.CREATED) ;
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
