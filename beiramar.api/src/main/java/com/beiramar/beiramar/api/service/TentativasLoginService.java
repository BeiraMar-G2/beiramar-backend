package com.beiramar.beiramar.api.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TentativasLoginService {

    private static final int maxTentativas = 3;
    private static final int minutosBloqueio = 15;

    private final Map<String, Integer> tentativas = new ConcurrentHashMap<>();
    private final Map<String, LocalDateTime> bloqueados = new ConcurrentHashMap<>();

    public void registrarFalha(String email) {
        int novaQtd = tentativas.getOrDefault(email, 0) + 1;
        tentativas.put(email, novaQtd);

        if (novaQtd >= maxTentativas) {
            bloqueados.put(email, LocalDateTime.now().plusMinutes(minutosBloqueio));
            tentativas.remove(email);
        }
    }

    public void limparTentativas(String email) {
        tentativas.remove(email);
        bloqueados.remove(email);
    }

    public boolean estaBloqueado(String email) {
        LocalDateTime tempoBloqueio = bloqueados.get(email);

        if (tempoBloqueio == null) {
            return false;
        }

        if (LocalDateTime.now().isAfter(tempoBloqueio)) {
            bloqueados.remove(email);
            return false;
        }

        return true;
    }

    public long minutosRestantes(String email) {
        LocalDateTime tempo = bloqueados.get(email);
        if (tempo == null) return 0;
        return java.time.Duration.between(LocalDateTime.now(), tempo).toMinutes();
    }
}
