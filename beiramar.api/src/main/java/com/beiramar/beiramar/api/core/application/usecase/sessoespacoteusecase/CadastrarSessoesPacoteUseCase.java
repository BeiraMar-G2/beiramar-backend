package com.beiramar.beiramar.api.core.application.usecase.sessoespacoteusecase;

import com.beiramar.beiramar.api.core.adapter.SessoesPacoteGateway;
import com.beiramar.beiramar.api.core.application.command.sessoespacotecommand.SessoesPacoteCadastroCommand;
import com.beiramar.beiramar.api.core.domain.Pacote;
import com.beiramar.beiramar.api.core.domain.Servico;
import com.beiramar.beiramar.api.core.domain.SessoesPacote;

public class CadastrarSessoesPacoteUseCase {

    private final SessoesPacoteGateway sessoesPacoteGateway;

    public CadastrarSessoesPacoteUseCase(SessoesPacoteGateway sessoesPacoteGateway) {
        this.sessoesPacoteGateway = sessoesPacoteGateway;
    }

    public SessoesPacote executar(SessoesPacoteCadastroCommand command){

        Pacote pacote = sessoesPacoteGateway.buscarPacotePorId(command.getFkPacote());
        Servico servico = sessoesPacoteGateway.buscarServicoPorId(command.getFkServico());

        SessoesPacote sessoesPacote = new SessoesPacote(
                command.getQtdSessoes(),
                pacote,
                servico
        );

        return sessoesPacoteGateway.salvar(sessoesPacote);
    }
}
