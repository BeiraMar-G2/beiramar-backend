package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.application.command.agendamentocommand.AgendamentoAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.sessoespacotecommand.SessoesPacoteAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Agendamento;
import com.beiramar.beiramar.api.core.domain.SessoesPacote;

public class AtualizarAgendamentoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public AtualizarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    public Agendamento executar(Integer id, AgendamentoAtualizacaoCommand command){
        Agendamento agendamento = agendamentoGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Agendamento não encontrado"));
        agendamento.atualizar(command.getDtHora(),
                command.getValorPago(),
                command.getStatus(),
                command.getStatusAgendamento());
        return agendamentoGateway.salvar(agendamento);
    }
}
