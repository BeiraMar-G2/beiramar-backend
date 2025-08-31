package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.command.servicocommand.ServicoCadastroCommand;
import com.beiramar.beiramar.api.core.domain.Servico;

public class CadastrarServicoUseCase {

    private final ServicoGateway servicoGateway;

    public CadastrarServicoUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    public Servico executar(ServicoCadastroCommand command) {
        Servico servico = new Servico(command.getNome(), command.getPreco(),
                command.getDescricao(), command.getDuracao());
        return servicoGateway.salvar(servico);
    }
}
