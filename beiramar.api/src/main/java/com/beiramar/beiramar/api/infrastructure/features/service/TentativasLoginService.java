package com.beiramar.beiramar.api.infrastructure.features.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class TentativasLoginService {

    private static final int MAX_TENTATIVAS = 3;
    private static final int BLOQUEIO_MINUTOS = 15;

    private final RedisTemplate<String, Object> redisTemplate;

    public TentativasLoginService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void registrarFalha(String email) {
        String keyTentativas = "tentativas:" + email;
        String keyBloqueio = "bloqueio:" + email;

        // Incrementa o contador de tentativas no Redis
        Long tentativas = redisTemplate.opsForValue().increment(keyTentativas);

        if (tentativas == 1) {
            // Define expiração das tentativas (por ex: 15 minutos)
            redisTemplate.expire(keyTentativas, BLOQUEIO_MINUTOS, TimeUnit.MINUTES);
        }

        if (tentativas >= MAX_TENTATIVAS) {
            // Bloqueia o usuário por 15 minutos
            redisTemplate.opsForValue().set(keyBloqueio,
                    LocalDateTime.now().plusMinutes(BLOQUEIO_MINUTOS).toString(),
                    BLOQUEIO_MINUTOS,
                    TimeUnit.MINUTES);

            // Remove contador de tentativas
            redisTemplate.delete(keyTentativas);
        }
    }

    public void limparTentativas(String email) {
        redisTemplate.delete("tentativas:" + email);
        redisTemplate.delete("bloqueio:" + email);
    }

    public boolean estaBloqueado(String email) {
        String keyBloqueio = "bloqueio:" + email;
        Object valor = redisTemplate.opsForValue().get(keyBloqueio);

        if (valor == null) return false;

        LocalDateTime tempoBloqueio = LocalDateTime.parse(valor.toString());

        if (LocalDateTime.now().isAfter(tempoBloqueio)) {
            redisTemplate.delete(keyBloqueio);
            return false;
        }

        return true;
    }

    public long minutosRestantes(String email) {
        String keyBloqueio = "bloqueio:" + email;
        Object valor = redisTemplate.opsForValue().get(keyBloqueio);

        if (valor == null) return 0;

        LocalDateTime tempo = LocalDateTime.parse(valor.toString());
        return Duration.between(LocalDateTime.now(), tempo).toMinutes();
    }
}
