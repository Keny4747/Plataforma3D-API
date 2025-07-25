package com.plataforma3d.plataforma3D2024.controller;

import com.plataforma3d.plataforma3D2024.dto.ModeloGeneradoDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/send")
public class TrellisController {

    private final String TRELLIS_ENDPOINT = "https://wcriagdno28gu2-5000.proxy.runpod.net/process";
    private ModeloGeneradoDTO modeloGenerado;

    private final Map<String, ModeloGeneradoDTO> modelosGenerados = new ConcurrentHashMap<>();

    // Recibir el webhook con el ID
    @PostMapping("/webhook")
    public ResponseEntity<ModeloGeneradoDTO> recibirWebhook(@RequestBody ModeloGeneradoDTO dto) {
        // Mostrar en consola el contenido del dto SIEMPRE
        System.out.println("== Webhook recibido ==");
        System.out.println("Nombre: " + dto.getNombre());
        System.out.println("URL: " + dto.getUrl());
        System.out.println("ID: " + dto.getId());

        // Validación
        if (dto.getId() == null || dto.getId().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        modelosGenerados.put(dto.getId(), dto);
        return ResponseEntity.ok(dto);
    }

    // Obtener el modelo generado a partir del ID
    @GetMapping("/webhook")
    public ResponseEntity<ModeloGeneradoDTO> obtenerModeloGenerado(@RequestParam("id") String id) {
        ModeloGeneradoDTO dto = modelosGenerados.get(id);
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dto);
    }

    // Enviar la imagen a Trellis y manejar el ID
    @PostMapping("/procesar-imagen")
    public ResponseEntity<String> enviarImagen(@RequestParam("image") MultipartFile file,
                                               @RequestParam("id") String id) {
        try {
            // Incluimos el ID en el request de Trellis
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("image", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));
            body.add("id", id); // Pasar el id como parámetro a Trellis

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(TRELLIS_ENDPOINT, requestEntity, String.class);

            return ResponseEntity.ok("Imagen enviada correctamente: " + response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar imagen: " + e.getMessage());
        }
    }

    // Clase personalizada para recursos de archivo multipart
    static class MultipartInputStreamFileResource extends InputStreamResource {
        private final String filename;

        public MultipartInputStreamFileResource(InputStream inputStream, String filename) {
            super(inputStream);
            this.filename = filename;
        }

        @Override
        public String getFilename() {
            return this.filename;
        }

        @Override
        public long contentLength() throws IOException {
            return -1;
        }
    }
}
