package com.beiramar.beiramar.api.core.application.usecase.pacoteusecase;

import com.beiramar.beiramar.api.core.adapter.PacoteGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Pacote;

public class BuscarPacotePorIdUseCase {

    private final PacoteGateway pacoteGateway;

    public BuscarPacotePorIdUseCase(PacoteGateway pacoteGateway) {
        this.pacoteGateway = pacoteGateway;
    }

    public Pacote executar(Integer id){
        return pacoteGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pacote não encontrado"));
    }
}
