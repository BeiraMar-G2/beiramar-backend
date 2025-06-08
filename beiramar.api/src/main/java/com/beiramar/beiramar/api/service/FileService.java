package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.entity.FilesEntity;
import com.beiramar.beiramar.api.model.FileModel;
import com.beiramar.beiramar.api.repository.FilesEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;


@Slf4j
@Service
public class FileService {

    private final StorageService storageService;
    private final FilesEntityRepository fileEntityRepository;

    public FileService(StorageService storageService, FilesEntityRepository fileEntityRepository) {
        this.storageService = storageService;
        this.fileEntityRepository = fileEntityRepository;
    }


    @Transactional
    public FilesEntity save(MultipartFile file) {
        try {
            FilesEntity fileEntity = createFileEntity(file);
            FilesEntity savedEntity = fileEntityRepository.save(fileEntity);

            storageService.save(savedEntity.getStoredName(), file.getBytes());

            return savedEntity;
        } catch (IOException e) {
            log.error("[FILE-ERROR] Failed to save file", e);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Failed to save file due to IO issues", e);
        }
    }


    public FileModel load(int fileId) {
        FilesEntity fileEntity = this.fileEntityRepository.findById(fileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found"));

        byte[] bytes = storageService.load(fileEntity.getStoredName());
        ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);

        FileModel model = createFileModel(fileEntity, bytes);

        return model;
    }


    public void delete(int fileId) {
        FilesEntity fileEntity = this.fileEntityRepository.findById(fileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found"));

        storageService.delete(fileEntity.getStoredName());
        this.fileEntityRepository.delete(fileEntity);
    }

    private FilesEntity createFileEntity(MultipartFile file) {
        FilesEntity fileEntity = new FilesEntity();
        fileEntity.setOriginalName(file.getOriginalFilename());
        fileEntity.setSize(file.getSize());
        fileEntity.setContentType(file.getContentType());
        fileEntity.setStoredName(generateStoredName(file));

        return fileEntity;
    }

    private String generateStoredName(MultipartFile file) {
        return System.currentTimeMillis() + "_" + file.getOriginalFilename();
    }

    private FileModel createFileModel(FilesEntity fileEntity, byte[] bytes) {
        ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);

        FileModel model = new FileModel();
        model.setName(fileEntity.getOriginalName());
        model.setContentType(fileEntity.getContentType());
        model.setSize(fileEntity.getSize());
        model.setContent(byteArrayResource);

        return model;
    }
}
