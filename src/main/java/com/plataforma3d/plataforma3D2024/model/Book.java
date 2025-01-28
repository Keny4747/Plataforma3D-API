package com.plataforma3d.plataforma3D2024.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;

    private String coverPath;
    private String filePath;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;

    @PrePersist
    void initCreatedAt() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void initUpdatedAt() {
        updatedAt = LocalDateTime.now();
    }

}
