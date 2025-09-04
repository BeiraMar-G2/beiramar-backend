package com.beiramar.beiramar.api.core.application.usecase.valorpacoteusecase;

import com.beiramar.beiramar.api.core.adapter.ValorPacoteGateway;
import com.beiramar.beiramar.api.core.application.command.valorpacotecommand.ValorPacoteAtulizacaoCommand;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.ValorPacote;

public class AtualizarValorPacoteUseCase {

    private final ValorPacoteGateway valorPacoteGateway;

    public AtualizarValorPacoteUseCase(ValorPacoteGateway valorPacoteGateway) {
        this.valorPacoteGateway = valorPacoteGateway;
    }

    public ValorPacote executar(Integer id, ValorPacoteAtulizacaoCommand command){
        ValorPacote valorPacote = valorPacoteGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Valor-Pacote não encontrado"));
        valorPacote.atualizar(command.getValorTotal());
        return valorPacoteGateway.salvar(valorPacote);
    }
}
