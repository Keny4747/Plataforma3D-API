package com.plataforma3d.plataforma3D2024.model;

import com.plataforma3d.plataforma3D2024.model.utilitario.UnidadAprendizaje;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Modelo3D {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String url;
    private Boolean esExterno;
    private String embedCode;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidad_aprendizaje")
    private UnidadAprendizaje unidadAprendizaje;

    @ManyToOne
    private Docente subidoPor;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;
    @PrePersist
    void initCreatedAt() {
        createdAt = LocalDateTime.now();
    }


}
