package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

import java.util.List;

public class BuscarTop3ServicosMenosAgendadosUseCase {

    private final ServicoGateway servicoGateway;

    public BuscarTop3ServicosMenosAgendadosUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    public List<Object[]> executar() {
        List<Object[]> top3ServicosMenosAgendados = servicoGateway.buscarTop3ServicosMenosAgendados();

        if (top3ServicosMenosAgendados.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum serviço encontrado");
        }

        return top3ServicosMenosAgendados;
    }
}
