package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.command.servicocommand.ServicoAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Servico;

public class AtualizarServicoUseCase {

    private final ServicoGateway servicoGateway;

    public AtualizarServicoUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    public Servico executar(Integer id, ServicoAtualizacaoCommand command) {
        Servico servico = servicoGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço não encontrado"));

        servico.atualizar(command.getNome(), command.getPreco(), command.getDescricao(), command.getDuracao());
        return servicoGateway.salvar(servico);
    }
}
