package com.plataforma3d.plataforma3D2024.controller;

import com.plataforma3d.plataforma3D2024.dto.DocenteDTO;
import com.plataforma3d.plataforma3D2024.service.IDocenteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/docente")
public class DocenteController {
    private final IDocenteService docenteService;

    public DocenteController(IDocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<DocenteDTO>> docente() throws Exception {
        return new ResponseEntity<>( docenteService.readAll(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<DocenteDTO> createDocente(@Valid @RequestBody DocenteDTO docenteDto) throws Exception {
        return new ResponseEntity<>(docenteService.save(docenteDto), HttpStatus.CREATED) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> getDocenteById(@PathVariable Integer id) throws Exception {
        DocenteDTO docenteDTO = docenteService.findById(id);
        if (docenteDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(docenteService.findById(id), HttpStatus.OK) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> updateDocente(@PathVariable Integer id, @RequestBody DocenteDTO docenteDto) throws Exception {
        return new ResponseEntity<>(docenteService.update(id, docenteDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable Integer id) throws Exception {
        docenteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
