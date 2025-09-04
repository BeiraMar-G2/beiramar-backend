package com.beiramar.beiramar.api.core.application.usecase.sessoespacoteusecase;

import com.beiramar.beiramar.api.core.adapter.SessoesPacoteGateway;
import com.beiramar.beiramar.api.core.application.command.sessoespacotecommand.SessoesPacoteAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.valorpacotecommand.ValorPacoteAtulizacaoCommand;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.SessoesPacote;
import com.beiramar.beiramar.api.core.domain.ValorPacote;

public class AtualizarSessoesPacoteUseCase {

    private final SessoesPacoteGateway sessoesPacoteGateway;

    public AtualizarSessoesPacoteUseCase(SessoesPacoteGateway sessoesPacoteGateway) {
        this.sessoesPacoteGateway = sessoesPacoteGateway;
    }

    public SessoesPacote executar(Integer id, SessoesPacoteAtualizacaoCommand command){
        SessoesPacote sessoesPacote = sessoesPacoteGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Sessões-Pacote não encontrado"));
        sessoesPacote.atualizar(command.getQtdSessoes());
        return sessoesPacoteGateway.salvar(sessoesPacote);
    }
}
