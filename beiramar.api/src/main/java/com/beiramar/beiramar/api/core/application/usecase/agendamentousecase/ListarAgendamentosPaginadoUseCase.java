package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Agendamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ListarAgendamentosPaginadoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public ListarAgendamentosPaginadoUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    public Page<Agendamento> executar(Pageable pageable) {
        Page<Agendamento> agendamentos = agendamentoGateway.listarTodosPaginado(pageable);

        if (agendamentos.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum agendamento cadastrado");
        }

        return agendamentos;
    }
}
