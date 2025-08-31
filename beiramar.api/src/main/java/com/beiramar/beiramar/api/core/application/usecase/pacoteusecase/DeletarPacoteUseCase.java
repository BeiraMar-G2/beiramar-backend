package com.beiramar.beiramar.api.core.application.usecase.pacoteusecase;

import com.beiramar.beiramar.api.core.adapter.PacoteGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

public class DeletarPacoteUseCase {

    private final PacoteGateway pacoteGateway;

    public DeletarPacoteUseCase(PacoteGateway pacoteGateway) {
        this.pacoteGateway = pacoteGateway;
    }

    public void executar(Integer id){
        if (!pacoteGateway.existePorId(id)){
            throw new EntidadeNaoEncontradaException("Pacote não encontrado");
        }
        pacoteGateway.deletar(id);
    }
}
