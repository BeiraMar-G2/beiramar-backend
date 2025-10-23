package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Agendamento;

import java.time.LocalDateTime;
import java.util.List;

public class ListarAgendamentosHistoricoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public ListarAgendamentosHistoricoUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    public List<Agendamento> executar(Integer idCliente, LocalDateTime data) {
        List<Agendamento> historico = agendamentoGateway.listarHistoricoPorIdCliente(idCliente, data);
        if (historico.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum Agendamento Encontrado");
        }
        return historico;
    }
}
