package com.beiramar.beiramar.api.infrastructure.features.repository;

import com.beiramar.beiramar.api.infrastructure.features.entity.LogSenhaEntity;
import com.beiramar.beiramar.api.infrastructure.features.entity.StatusLogSenha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogSenhaRepository extends JpaRepository<LogSenhaEntity, Integer> {
    Optional<LogSenhaEntity> findTopByTokenAndUsuarioEmailOrderByDataHoraLogSenhaDesc(String token, String email);

    Optional<LogSenhaEntity> findTopByUsuarioEmailAndStatusOrderByDataHoraLogSenhaDesc(String email, StatusLogSenha status);

}
