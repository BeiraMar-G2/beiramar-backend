package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Agendamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public class ListarAgendamentosHistoricoPaginadoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public ListarAgendamentosHistoricoPaginadoUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    public Page<Agendamento> executar(Integer idCliente, LocalDateTime data, Pageable pageable) {
        Page<Agendamento> agendamentos = agendamentoGateway.listarHistoricoPorIdClientePaginado(idCliente, data, pageable);

        if (agendamentos.isEmpty()){
            throw new EntidadeNaoEncontradaException("Agendamento não encontrado");
        }
        return agendamentos;
    }
}
