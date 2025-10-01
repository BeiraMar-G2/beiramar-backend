package com.beiramar.beiramar.api.service.messaging;

import com.beiramar.beiramar.api.config.RabbitMQConfig;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.entity.LogSenhaEntity;
import com.beiramar.beiramar.api.repository.LogSenhaRepository;
import com.beiramar.beiramar.api.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private final EmailService emailService;
    private final LogSenhaRepository logSenhaRepository;

    public EmailConsumer(EmailService emailService, LogSenhaRepository logSenhaRepository) {
        this.emailService = emailService;
        this.logSenhaRepository = logSenhaRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void processEmail(EmailMessageDto message) {
        LogSenhaEntity log = logSenhaRepository.findById(message.getLogId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("LogSenha não encontrado"));

        emailService.enviarCodigo(message.getEmail(), message.getCodigo(), log);
    }
}
