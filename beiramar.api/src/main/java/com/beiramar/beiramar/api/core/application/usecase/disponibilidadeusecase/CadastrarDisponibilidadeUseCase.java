package com.beiramar.beiramar.api.core.application.usecase.disponibilidadeusecase;

import com.beiramar.beiramar.api.core.adapter.DisponibilidadeGateway;
import com.beiramar.beiramar.api.core.application.command.disponibilidadecommand.DisponibilidadeCadastroCommand;
import com.beiramar.beiramar.api.core.domain.Disponibilidade;
import com.beiramar.beiramar.api.core.domain.Usuario;

public class CadastrarDisponibilidadeUseCase {

    private final DisponibilidadeGateway disponibilidadeGateway;

    public CadastrarDisponibilidadeUseCase(DisponibilidadeGateway disponibilidadeGateway) {
        this.disponibilidadeGateway = disponibilidadeGateway;
    }

    public Disponibilidade executar(DisponibilidadeCadastroCommand command){

        Usuario funcionario = disponibilidadeGateway.buscarFuncionarioPorId(command.getFkFuncionario());

        Disponibilidade disponibilidade = new Disponibilidade(
                command.getDiaSemana(),
                command.getHoraInicio(),
                command.getHoraFim(),
                command.getDiaMes(),
                funcionario,
                command.getDisponibilidadeExcecao(),
                command.getFkFuncionarioExcecaoNome()
        );
        return disponibilidadeGateway.salvar(disponibilidade);
    }
}
