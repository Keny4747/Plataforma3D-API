package com.plataforma3d.plataforma3D2024.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@Service
public class FileSystemStorageService {

    @Value("${storage.location}")
    private String storageLocation;

    public void init() {
        try {
            Files.createDirectories(Paths.get(storageLocation));
        } catch (IOException ex) {
            throw new RuntimeException("No se pudo crear el directorio de almacenamiento: " + storageLocation, ex);
        }
    }

    public String store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("No se puede almacenar un archivo vacío.");
        }

        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(originalFilename);

        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, Paths.get(storageLocation).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException("Error al almacenar el archivo: " + originalFilename, ex);
        }

        return filename;
    }

    public Resource loadAsResource(String filename) {
        try {
            Path path = Paths.get(storageLocation).resolve(filename);
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se pudo leer el archivo: " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Error al cargar el archivo: " + filename, ex);
        }
    }

    public void delete(String filename) {
        try {
            Path path = Paths.get(storageLocation).resolve(filename);
            FileSystemUtils.deleteRecursively(path);
        } catch (IOException ex) {
            // Ignorar el error si el archivo no existe
        }
    }
}
