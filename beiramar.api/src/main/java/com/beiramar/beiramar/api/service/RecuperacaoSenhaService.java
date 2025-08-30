package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.entity.LogSenhaEntity;
import com.beiramar.beiramar.api.entity.StatusLogSenha;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.repository.LogSenhaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class RecuperacaoSenhaService {

    @Autowired
    private UsuarioJpaRepository usuarioRepository;

    @Autowired
    private LogSenhaRepository logSenhaRepository;

    @Autowired
    private EmailService emailService;



    public void enviarCodigo(String email){
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findByEmail(email);
        // Se existir, gera e envia o código
        usuarioOptional.ifPresent(usuario -> {
            String codigo = String.format("%06d", new Random().nextInt(1_000_000));

            LogSenhaEntity log = new LogSenhaEntity();
            log.setToken(codigo);
            log.setUsuario(usuario);
            log.setStatus(StatusLogSenha.COD_CRIADO);
            log.setDataHoraLogSenha(LocalDateTime.now());
            logSenhaRepository.save(log);

            emailService.enviarCodigo(email, codigo, log);
        });
    }

    public boolean validarCodigo(String email, String codigo){
        Optional<LogSenhaEntity> logOptional = logSenhaRepository.
                findTopByTokenAndUsuarioEmailOrderByDataHoraLogSenhaDesc(codigo, email);
        if(logOptional.isEmpty()){
            return false;
        }

        LogSenhaEntity log = logOptional.get();

        LocalDateTime agora = LocalDateTime.now();
        if (log.getDataHoraLogSenha().plusMinutes(10).isBefore(agora)) {
            return false; // Código expirado
        }

        log.setStatus(StatusLogSenha.COD_ALTENTICADO);
        logSenhaRepository.save(log);
        emailService.enviarAutenticacao(email);
        return true;

    }


}
