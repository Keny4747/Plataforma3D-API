package com.plataforma3d.plataforma3D2024.service.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@Service
public class S3Service {
    private final S3Client s3Client;

    @Value("${do.spaces.bucket}")
    private String bucketName;

    @Value("${do.spaces.endpoint}")
    private String endpoint;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) {
        try {
            String fileName = "modelos3D/" + UUID.randomUUID() + "_" + file.getOriginalFilename();

            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .acl("public-read")
                            .build(),
                    RequestBody.fromBytes(file.getBytes()));  // Usar bytes en lugar de Paths

            return endpoint + "/" + bucketName + "/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("Error al subir archivo a DigitalOcean Spaces", e);
        }
    }

}
