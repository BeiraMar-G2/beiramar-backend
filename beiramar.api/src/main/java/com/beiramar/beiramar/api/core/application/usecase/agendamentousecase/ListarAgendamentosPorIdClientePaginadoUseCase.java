package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Agendamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ListarAgendamentosPorIdClientePaginadoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public ListarAgendamentosPorIdClientePaginadoUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }


    public Page<Agendamento> executar(Integer idCliente, Pageable pageable) {
        Page<Agendamento> agendamentos = agendamentoGateway.listarPorIdClientePaginado(idCliente, pageable);

        if (agendamentos.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum agendamento encontrado para o cliente com ID: " + idCliente);
        }
        return agendamentos;
    }
}
