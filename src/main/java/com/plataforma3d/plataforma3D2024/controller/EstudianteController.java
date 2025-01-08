package com.plataforma3d.plataforma3D2024.controller;

import com.plataforma3d.plataforma3D2024.dto.DocenteDTO;
import com.plataforma3d.plataforma3D2024.dto.EstudianteDTO;
import com.plataforma3d.plataforma3D2024.exceptions.ModeloNotFoundException;
import com.plataforma3d.plataforma3D2024.service.IDocenteService;
import com.plataforma3d.plataforma3D2024.service.IEstudianteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<EstudianteDTO>> docente() throws Exception {
        return new ResponseEntity<>(estudianteService.readAll(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<EstudianteDTO> createDocente(@Valid @RequestBody EstudianteDTO estudianteDTO) throws Exception {
        return new ResponseEntity<>(estudianteService.save(estudianteDTO), HttpStatus.CREATED) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO>  getDocenteById(@PathVariable Integer id) throws Exception {

        EstudianteDTO estudianteDTO = estudianteService.findById(id);
        if (estudianteDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(estudianteDTO, HttpStatus.OK) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> updateDocente(@PathVariable Integer id, @RequestBody EstudianteDTO estudianteDTO) throws Exception {
        return new ResponseEntity<>(estudianteService.update(id, estudianteDTO), HttpStatus.OK) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteDocente(@PathVariable Integer id) throws Exception {
         estudianteService.delete(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
