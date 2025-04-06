package com.plataforma3d.plataforma3D2024.controller;

import com.plataforma3d.plataforma3D2024.service.aws.S3Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/s3")
public class S3ControllerDo {

    private final S3Service s3Service;

    public S3ControllerDo(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public Map<String, List<String>> upload(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam(value = "tipo", required = false, defaultValue = "modelos3D") String tipo) {

        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = s3Service.uploadFile(file, tipo);
            urls.add(url);
        }
        return Map.of("filenames", urls);
    }

}
