package com.beiramar.beiramar.api.controller;


import com.amazonaws.services.s3.AmazonS3;
import com.beiramar.beiramar.api.entity.FilesEntity;
import com.beiramar.beiramar.api.model.FileModel;
import com.beiramar.beiramar.api.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    private final AmazonS3 s3Client;
    private final Environment environment;

    public FileController(FileService fileService, AmazonS3 s3Client, Environment environment) {
        this.fileService = fileService;
        this.s3Client = s3Client;
        this.environment = environment;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FilesEntity> uploadFile(@RequestParam MultipartFile file) {
        FilesEntity save = fileService.save(file);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();

        return ResponseEntity.created(uri).body(save);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(
            @PathVariable Integer id
    ) {

        FileModel file = this.fileService.load(id);

        final String contentTypeValue = "attachment; filename=" + file.getName();

        return ResponseEntity.status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentTypeValue)
                .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.getSize()))
                .body(file.getContent());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(
            @PathVariable Integer id
    ) {
        this.fileService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/debug-config")
    public ResponseEntity<Map<String, Object>> debugConfig(
            @Value("${app.s3.bucket:NOT_FOUND}") String bucket,
            @Value("${app.s3.access-key:NOT_FOUND}") String accessKey,
            @Value("${app.s3.secret-key:NOT_FOUND}") String secretKey,
            @Value("${app.s3.region:NOT_FOUND}") String region
    ) {
        Map<String, Object> config = new HashMap<>();
        config.put("bucket", bucket);
        config.put("region", region);
        config.put("accessKeyConfigured", !accessKey.equals("NOT_FOUND") && !accessKey.isEmpty());
        config.put("secretKeyConfigured", !secretKey.equals("NOT_FOUND") && !secretKey.isEmpty());
        config.put("accessKeyPreview", accessKey.length() > 4 ? accessKey.substring(0, 4) + "..." : "EMPTY");

        return ResponseEntity.ok(config);
    }

    @GetMapping("/test-s3")
    public ResponseEntity<Map<String, Object>> testS3Connection() {
        Map<String, Object> result = new HashMap<>();
        try {
            // Testa se consegue listar os buckets
            var buckets = s3Client.listBuckets();
            result.put("status", "success");
            result.put("message", "S3 connection successful!");
            result.put("bucketsCount", buckets.size());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "S3 connection failed: " + e.getMessage());
            result.put("errorType", e.getClass().getSimpleName());
            return ResponseEntity.status(503).body(result);
        }
    }
}
