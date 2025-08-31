package com.beiramar.beiramar.api.core.application.usecase.pacoteusecase;

import com.beiramar.beiramar.api.core.adapter.PacoteGateway;
import com.beiramar.beiramar.api.core.application.command.pacotecommand.PacoteCadastroCommand;
import com.beiramar.beiramar.api.core.domain.Pacote;

public class CadastrarPacoteUseCase {

    private final PacoteGateway pacoteGateway;

    public CadastrarPacoteUseCase(PacoteGateway pacoteGateway) {
        this.pacoteGateway = pacoteGateway;
    }

    public Pacote executar(PacoteCadastroCommand command){
        Pacote pacote = new Pacote(command.getNome(),
                command.getPrecoTotalSemDesconto(),
                command.getQtdSessoesTotal(),
                command.getTempoLimiteDias());

        return pacoteGateway.salvar(pacote);

    }
}
