package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.entity.LogSenha;
import com.beiramar.beiramar.api.entity.StatusLogSenha;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.repository.LogSenhaRepository;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class RecuperacaoSenhaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LogSenhaRepository logSenhaRepository;

    @Autowired
    private EmailService emailService;



    public void enviarCodigo(String email){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        // Se existir, gera e envia o código
        usuarioOptional.ifPresent(usuario -> {
            String codigo = String.format("%06d", new Random().nextInt(1_000_000));

            LogSenha log = new LogSenha();
            log.setToken(codigo);
            log.setUsuario(usuario);
            log.setStatus(StatusLogSenha.COD_CRIADO);
            log.setDataHoraLogSenha(LocalDateTime.now());
            logSenhaRepository.save(log);

            emailService.enviarCodigo(email, codigo, log);
        });
    }

    public boolean validarCodigo(String email, String codigo){
        Optional<LogSenha> logOptional = logSenhaRepository.
                findTopByTokenAndUsuarioEmailOrderByDataHoraLogSenhaDesc(codigo, email);
        if(logOptional.isEmpty()){
            return false;
        }

        LogSenha log = logOptional.get();

        LocalDateTime agora = LocalDateTime.now();
        if (log.getDataHoraLogSenha().plusMinutes(10).isBefore(agora)) {
            return false; // Código expirado
        }

        log.setStatus(StatusLogSenha.COD_ALTENTICADO);
        logSenhaRepository.save(log);

        return true;

    }


}
