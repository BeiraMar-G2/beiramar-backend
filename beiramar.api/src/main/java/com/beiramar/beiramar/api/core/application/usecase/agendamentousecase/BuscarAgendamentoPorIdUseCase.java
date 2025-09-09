package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Agendamento;
import com.beiramar.beiramar.api.core.domain.SessoesPacote;

public class BuscarAgendamentoPorIdUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public BuscarAgendamentoPorIdUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    public Agendamento executar(Integer id) {
        return agendamentoGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Agendamento não encontrado"));
    }
}
