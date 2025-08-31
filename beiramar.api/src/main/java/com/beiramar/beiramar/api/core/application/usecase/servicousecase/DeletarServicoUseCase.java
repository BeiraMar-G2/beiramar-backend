package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

public class DeletarServicoUseCase {

    private final ServicoGateway servicoGateway;

    public DeletarServicoUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    public void executar(Integer id) {
        if (!servicoGateway.existePorId(id)) {
            throw new EntidadeNaoEncontradaException("Serviço não encontrado");
        }
        servicoGateway.deletar(id);
    }
}
