package com.beiramar.beiramar.api.repository;

import com.beiramar.beiramar.api.entity.LogSenhaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogSenhaRepository extends JpaRepository<LogSenhaEntity, Long> {
    Optional<LogSenhaEntity> findTopByTokenAndUsuarioEmailOrderByDataHoraLogSenhaDesc(String token, String email);
}
