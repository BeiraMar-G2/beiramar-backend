package com.beiramar.beiramar.api.infrastructure.features.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


@Slf4j
@Service
@Profile("!s3")
public class FileSystemStorageService implements StorageService {

    private final String uploadPath;

    public FileSystemStorageService(@Value("${app.upload.path:./uploads}") String uploadPath) {
        this.uploadPath = uploadPath;
        initializeUploadDirectory();
    }

    @Override
    public void save(String fileName, byte[] content) {
        try {
            Path filePath = Paths.get(uploadPath, fileName);
            Files.write(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            log.info("File saved successfully: {}", fileName);
        } catch (IOException e) {
            log.error("Failed to save file: {}", fileName, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save file", e);
        }
    }

    @Override
    public byte[] load(String fileName) {
        try {
            Path filePath = Paths.get(uploadPath, fileName);
            if (!Files.exists(filePath)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found in storage: " + fileName);
            }
            byte[] fileBytes = Files.readAllBytes(filePath);
            log.info("File loaded successfully: {}", fileName);
            return fileBytes;
        } catch (IOException e) {
            log.error("Failed to load file: {}", fileName, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to load file", e);
        }
    }

    @Override
    public void delete(String fileName) {
        try {
            Path filePath = Paths.get(uploadPath, fileName);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                log.info("File deleted successfully: {}", fileName);
            } else {
                log.warn("Attempted to delete non-existent file: {}", fileName);
            }
        } catch (IOException e) {
            log.error("Failed to delete file: {}", fileName, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete file", e);
        }
    }


    private void initializeUploadDirectory() {
        try {
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
                log.info("Created upload directory: {}", uploadPath);
            }
        } catch (IOException e) {
            log.error("Failed to create upload directory: {}", uploadPath, e);
            throw new RuntimeException("Failed to initialize upload directory", e);
        }
    }
}