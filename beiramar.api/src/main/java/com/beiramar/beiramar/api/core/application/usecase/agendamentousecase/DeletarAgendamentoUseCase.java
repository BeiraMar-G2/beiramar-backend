package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

public class DeletarAgendamentoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public DeletarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    public void executar(Integer id) {
        if (!agendamentoGateway.existePorId(id)) {
            throw new EntidadeNaoEncontradaException("Agendamento não encontrado");
        }
        agendamentoGateway.deletar(id);
    }
}
