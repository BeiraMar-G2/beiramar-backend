package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

import java.util.List;

public class BuscarServicosMaisCanceladosUseCase {
    private final ServicoGateway servicoGateway;

    public BuscarServicosMaisCanceladosUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    public List<Object[]> executar() {
        List<Object[]> servicosMaisCancelados = servicoGateway.buscarServicosMaisCancelados();

        if (servicosMaisCancelados.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum serviço com cancelamentos encontrado");
        }

        return servicosMaisCancelados;
    }

}
