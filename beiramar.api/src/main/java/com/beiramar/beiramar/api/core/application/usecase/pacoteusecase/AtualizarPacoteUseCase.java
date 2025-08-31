package com.beiramar.beiramar.api.core.application.usecase.pacoteusecase;

import com.beiramar.beiramar.api.core.adapter.PacoteGateway;
import com.beiramar.beiramar.api.core.application.command.pacotecommand.PacoteAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Pacote;

public class AtualizarPacoteUseCase {

    private final PacoteGateway pacoteGateway;

    public AtualizarPacoteUseCase(PacoteGateway pacoteGateway) {
        this.pacoteGateway = pacoteGateway;
    }

    public Pacote executar(Integer id, PacoteAtualizacaoCommand command){
        Pacote pacote = pacoteGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pacote não encontrado"));

        pacote.atualizar(command.getNome(), command.getPrecoTotalSemDesconto(), command.getQtdSessoesTotal(), command.getTempoLimiteDias());
        return pacoteGateway.salvar(pacote);
    }
}
