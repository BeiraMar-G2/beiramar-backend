package com.beiramar.beiramar.api.repository;

import com.beiramar.beiramar.api.entity.FilesEntity;
import org.apache.http.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesEntityRepository extends JpaRepository<FilesEntity, Integer> {
}
