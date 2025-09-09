package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Agendamento;

import java.util.List;

public class ListarAgendamentosUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public ListarAgendamentosUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    public List<Agendamento> executar(){
        List<Agendamento> agendamentos = agendamentoGateway.listarTodos();

        if (agendamentos.isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhum agendamento cadastrado");
        }
        return agendamentos;
    }
}
