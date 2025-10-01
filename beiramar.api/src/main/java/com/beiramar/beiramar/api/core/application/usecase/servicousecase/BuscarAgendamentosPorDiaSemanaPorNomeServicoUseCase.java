package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

import java.util.List;

public class BuscarAgendamentosPorDiaSemanaPorNomeServicoUseCase {

    private final ServicoGateway servicoGateway;

    public BuscarAgendamentosPorDiaSemanaPorNomeServicoUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    public List<Object[]> executar(String nomeServico) {
        if (nomeServico == null || nomeServico.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do serviço é obrigatório");
        }

        List<Object[]> agendamentosPorDiaSemana = servicoGateway.buscarAgendamentosPorDiaSemanaPorNomeServico(nomeServico);

        if (agendamentosPorDiaSemana.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum agendamento encontrado para o serviço: " + nomeServico);
        }

        return agendamentosPorDiaSemana;
    }
}
