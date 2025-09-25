package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

import java.util.List;

public class BuscarTop3ServicosMaisAgendadosUseCase {

    private final ServicoGateway servicoGateway;

    public BuscarTop3ServicosMaisAgendadosUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    /**
     * Busca os top 3 serviços com mais agendamentos, mostrando o serviço e a quantidade
     * @return Lista com arrays contendo [posicao_ranking, nome_servico, quantidade_agendamentos]
     *         Ordenados do maior para o menor número de agendamentos (1º, 2º, 3º lugar)
     */
    public List<Object[]> executar() {
        List<Object[]> top3ServicosComQuantidade = servicoGateway.buscarTop3ServicosMaisAgendados();

        if (top3ServicosComQuantidade.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum serviço encontrado");
        }

        return top3ServicosComQuantidade;
    }
}
