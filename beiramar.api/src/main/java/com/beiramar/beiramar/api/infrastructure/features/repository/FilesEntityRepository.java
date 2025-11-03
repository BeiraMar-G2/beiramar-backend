package com.beiramar.beiramar.api.infrastructure.features.repository;

import com.beiramar.beiramar.api.infrastructure.features.entity.FilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesEntityRepository extends JpaRepository<FilesEntity, Integer> {
}
