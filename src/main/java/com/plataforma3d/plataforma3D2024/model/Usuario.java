package com.plataforma3d.plataforma3D2024.model;

import com.plataforma3d.plataforma3D2024.model.utilitario.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class Usuario {
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "telefono")
    private String telefono;

    @Column(name = "dni")
    private String dni;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleEnum role;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    void initCreatedAt() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void initUpdatedUt() {
        updatedAt = LocalDateTime.now();
    }
}
