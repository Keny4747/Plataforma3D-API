package com.plataforma3d.plataforma3D2024.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DocenteDTO {

    @NotBlank(message = "El nombre del docente no puede estar en blanco")
    @Size(min = 2, message = "El nombre del docente debe tener al menos 2 caracteres")
    private String nombre;

    @NotBlank(message = "El Apellido del docente no puede estar en blanco")
    @Size(min = 3, message = "El apellido del docente debe tener al menos 3 caracteres")
    private String apellido;

    @Email
    private String email;


    private String password;
    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;

    @NotBlank(message = "El DNI es obligatorio")
    private String dni;
    private String especialidad;
}
