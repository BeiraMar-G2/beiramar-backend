package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

import java.util.List;

public class BuscarCancelamentosPorDiaSemanaPorNomeServicoUseCase {

    private final ServicoGateway servicoGateway;

    public BuscarCancelamentosPorDiaSemanaPorNomeServicoUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    /**
     * Busca a quantidade de cancelamentos de um serviço específico por dias da semana
     * @param nomeServico Nome do serviço para buscar os cancelamentos
     * @return Lista com arrays contendo [dia_semana, quantidade_cancelamentos]
     *         Ordenados por ordem dos dias da semana (domingo a sábado)
     */
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
