package com.beiramar.beiramar.api.core.application.usecase.sessoespacoteusecase;

import com.beiramar.beiramar.api.core.adapter.SessoesPacoteGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.SessoesPacote;

public class BuscarSessoesPacotePorIdUseCase {

    private final SessoesPacoteGateway sessoesPacoteGateway;

    public BuscarSessoesPacotePorIdUseCase(SessoesPacoteGateway sessoesPacoteGateway) {
        this.sessoesPacoteGateway = sessoesPacoteGateway;
    }

    public SessoesPacote executar(Integer id) {
        return sessoesPacoteGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Sessoes-Pacote não encontrado"));
    }
}
