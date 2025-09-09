package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Agendamento;

import java.util.List;

public class ListarAgendamentosPorIdClienteUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public ListarAgendamentosPorIdClienteUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    public List<Agendamento> executar(Integer idCliente) {
        List<Agendamento> agendamentos = agendamentoGateway.listarPorIdCliente(idCliente);

        if (agendamentos.isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhum agendamento encontrado para o cliente com ID: " + idCliente);
        }
        return agendamentos;
    }
}
