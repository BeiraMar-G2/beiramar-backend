package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

import java.util.List;

public class BuscarCancelamentosPorDiaSemanaPorNomeServicoUseCase {

    private final ServicoGateway servicoGateway;

    public BuscarCancelamentosPorDiaSemanaPorNomeServicoUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    public List<Object[]> executar(String nomeServico) {
        if (nomeServico == null || nomeServico.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do serviço é obrigatório");
        }

        List<Object[]> cancelamentosPorDiaSemana = servicoGateway.buscarCancelamentosPorDiaSemanaPorNomeServico(nomeServico);

        if (cancelamentosPorDiaSemana.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum cancelamento encontrado para o serviço: " + nomeServico);
        }

        return cancelamentosPorDiaSemana;
    }
}
