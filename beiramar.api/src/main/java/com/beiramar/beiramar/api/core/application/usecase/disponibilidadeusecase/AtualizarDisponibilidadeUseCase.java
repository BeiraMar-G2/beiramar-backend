package com.beiramar.beiramar.api.core.application.usecase.disponibilidadeusecase;

import com.beiramar.beiramar.api.core.adapter.DisponibilidadeGateway;
import com.beiramar.beiramar.api.core.application.command.agendamentocommand.AgendamentoAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.disponibilidadecommand.DisponibilidadeAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Agendamento;
import com.beiramar.beiramar.api.core.domain.Disponibilidade;

public class AtualizarDisponibilidadeUseCase {

    private final DisponibilidadeGateway disponibilidadeGateway;

    public AtualizarDisponibilidadeUseCase(DisponibilidadeGateway disponibilidadeGateway) {
        this.disponibilidadeGateway = disponibilidadeGateway;
    }

    public Disponibilidade executar(Integer id, DisponibilidadeAtualizacaoCommand command){
        Disponibilidade disponibilidade = disponibilidadeGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Disponibilidade não encontrada"));
        disponibilidade.atualizar(command.getDiaSemana(),
                command.getHoraInicio(),
                command.getHoraFim(),
                command.getDiaMes(),
                command.getDisponibilidadeExcecao());

        return disponibilidadeGateway.salvar(disponibilidade);
    }
}
