package com.beiramar.beiramar.api.core.application.usecase.valorpacoteusecase;

import com.beiramar.beiramar.api.core.adapter.ValorPacoteGateway;
import com.beiramar.beiramar.api.core.application.command.valorpacotecommand.ValorPacoteCadastroCommand;
import com.beiramar.beiramar.api.core.domain.Pacote;
import com.beiramar.beiramar.api.core.domain.Usuario;
import com.beiramar.beiramar.api.core.domain.ValorPacote;

public class CadastrarValorPacoteUseCase {

    private final ValorPacoteGateway valorPacoteGateway;

    public CadastrarValorPacoteUseCase(ValorPacoteGateway valorPacoteGateway) {
        this.valorPacoteGateway = valorPacoteGateway;
    }

    public ValorPacote executar(ValorPacoteCadastroCommand command){

        Usuario usuario = valorPacoteGateway.buscarUsuarioPorId(command.getFkUsuario());
        Pacote pacote = valorPacoteGateway.buscarPacotePorId(command.getFkPacote());

        ValorPacote valorPacote = new ValorPacote(
                command.getValorTotal(),
                usuario,
                pacote
        );
        return valorPacoteGateway.salvar(valorPacote);
    }
}
