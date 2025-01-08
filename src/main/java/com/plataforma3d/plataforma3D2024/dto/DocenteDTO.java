package com.plataforma3d.plataforma3D2024.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DocenteDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty
    @Size(min = 2)
    private String nombre;

    @NotNull(message = "El Apellido no puede ser nulo")
    @NotEmpty
    @Size(min = 3)
    private String apellido;

    @Email
    private String email;

    private String password;

    private String telefono;
    private String dni;
    private String especialidad;
}
