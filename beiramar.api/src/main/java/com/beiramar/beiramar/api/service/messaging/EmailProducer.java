package com.beiramar.beiramar.api.service.messaging;

import com.beiramar.beiramar.api.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmailToQueue(EmailMessageDto message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, message);
    }
}
