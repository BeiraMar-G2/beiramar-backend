package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Agendamento;

import java.util.List;

public class ListarAgendamentosPorMesUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public ListarAgendamentosPorMesUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    public List<Agendamento> executar(int mes, int ano) {
        List<Agendamento> agendamento = agendamentoGateway.listarPorMes(mes, ano);

        if (agendamento.isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhum agendamento encontrado para o mes: " + mes);
        }

        return agendamento;
    }

}
