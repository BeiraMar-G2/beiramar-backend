package com.beiramar.beiramar.api.repository;

import com.beiramar.beiramar.api.entity.LogSenha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogSenhaRepository extends JpaRepository<LogSenha, Long> {
    Optional<LogSenha> findTopByTokenAndUsuarioEmailOrderByDataHoraLogSenhaDesc(String token, String email);
}
