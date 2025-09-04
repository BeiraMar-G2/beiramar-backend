package com.beiramar.beiramar.api.core.application.usecase.valorpacoteusecase;

import com.beiramar.beiramar.api.core.adapter.ValorPacoteGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

public class DeletarValorPacoteUseCase {

    private final ValorPacoteGateway valorPacoteGateway;

    public DeletarValorPacoteUseCase(ValorPacoteGateway valorPacoteGateway) {
        this.valorPacoteGateway = valorPacoteGateway;
    }

    public void executar(Integer id) {
        if (!valorPacoteGateway.existePorId(id)) {
            throw new EntidadeNaoEncontradaException("Valor-Pacote não encontrado");
        }
        valorPacoteGateway.deletar(id);
    }
}
