package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Servico;

public class BuscarServicoPorIdUseCase {

    private final ServicoGateway servicoGateway;

    public BuscarServicoPorIdUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    public Servico executar(Integer id) {
        return servicoGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço não encontrado"));
    }
}
