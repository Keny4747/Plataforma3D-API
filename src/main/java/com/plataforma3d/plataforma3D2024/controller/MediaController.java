package com.plataforma3d.plataforma3D2024.controller;

import com.plataforma3d.plataforma3D2024.service.impl.FileSystemStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final FileSystemStorageService storageService;

    public MediaController(FileSystemStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public Map<String, List<String>> upload(@RequestParam("files") List<MultipartFile> files) {
        List<String> filenames = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = storageService.store(file);
            filenames.add(filename);
        }
        return Map.of("filenames", filenames);
    }
    @GetMapping("/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String filename) {
        Resource resource = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
