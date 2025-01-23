package com.plataforma3d.plataforma3D2024.dto;

import com.plataforma3d.plataforma3D2024.model.utilitario.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EstudianteDTO {
    @NotBlank(message = "El nombre del estudiante no puede estar en blanco")
    @Size(min = 2, message = "El nombre del estudiante debe tener al menos 2 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido del estudiante no puede estar en blanco")
    @Size(min = 3, message = "El apellido del estudiante debe tener al menos 3 caracteres")
    private String apellido;

    @Email
    private String email;

    private String username;



    private String password;

    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;

    @NotBlank(message = "El DNI es obligatorio")
    private String dni;

    @NotNull(message = "El rol del usuario es obligatorio: ADMIN, USER")
    private RoleEnum role;
}
