package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

import java.time.LocalDateTime;
import java.util.List;

public class BuscarTop3ServicosMenosAgendadosUseCase {

    private final ServicoGateway servicoGateway;

    public BuscarTop3ServicosMenosAgendadosUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    public List<Object[]> executar(LocalDateTime dataInicio, LocalDateTime dataFim) {

        List<Object[]> top3MenosAgendados =
                servicoGateway.buscarTop3ServicosMenosAgendados(dataInicio, dataFim);

        if (top3MenosAgendados.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum serviço encontrado");
        }

        return top3MenosAgendados;
    }
}
