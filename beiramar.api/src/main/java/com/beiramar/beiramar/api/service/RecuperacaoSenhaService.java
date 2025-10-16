package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.entity.LogSenhaEntity;
import com.beiramar.beiramar.api.entity.StatusLogSenha;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import com.beiramar.beiramar.api.repository.LogSenhaRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecuperacaoSenhaService {

    @Autowired
    private UsuarioJpaRepository usuarioRepository;

    @Autowired
    private LogSenhaRepository logSenhaRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String QUEUE_NAME = "email-queue";

    public void enviarCodigo(String email) {
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findByEmail(email);

        usuarioOptional.ifPresent(usuario -> {
            // Gera código de 6 dígitos
            String codigo = String.format("%06d", new Random().nextInt(1_000_000));

            // Cria o log de envio
            LogSenhaEntity log = new LogSenhaEntity();
            log.setToken(codigo);
            log.setUsuario(usuario);
            log.setStatus(StatusLogSenha.COD_CRIADO);
            log.setDataHoraLogSenha(LocalDateTime.now());
            logSenhaRepository.save(log);

            // Monta o payload para enviar à fila
            Map<String, Object> payload = new HashMap<>();
            payload.put("email", email);
            payload.put("codigo", codigo);
            payload.put("logId", log.getIdLogSenha());

            // Publica na fila RabbitMQ
            rabbitTemplate.convertAndSend(QUEUE_NAME, payload);

            // Atualiza o status para enviado (com sucesso na publicação)
            log.setStatus(StatusLogSenha.COD_ENVIADO);
            logSenhaRepository.save(log);
        });
    }

    public boolean validarCodigo(String email, String codigo) {
        Optional<LogSenhaEntity> logOptional = logSenhaRepository
                .findTopByTokenAndUsuarioEmailOrderByDataHoraLogSenhaDesc(codigo, email);

        if (logOptional.isEmpty()) {
            return false;
        }

        LogSenhaEntity log = logOptional.get();
        LocalDateTime agora = LocalDateTime.now();

        // Verifica se o código expirou (10 minutos)
        if (log.getDataHoraLogSenha().plusMinutes(10).isBefore(agora)) {
            return false;
        }

        // Marca como autenticado
        log.setStatus(StatusLogSenha.COD_ALTENTICADO);
        logSenhaRepository.save(log);

        return true;
    }
}
