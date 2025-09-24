package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.adapter.PacoteGateway;
import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.application.command.agendamentocommand.AgendamentoCadastroCommand;
import com.beiramar.beiramar.api.core.domain.Agendamento;
import com.beiramar.beiramar.api.core.domain.Pacote;
import com.beiramar.beiramar.api.core.domain.Servico;
import com.beiramar.beiramar.api.core.domain.Usuario;

public class CadastrarAgendamentoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public CadastrarAgendamentoUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    public Agendamento executar(AgendamentoCadastroCommand command) {

        Usuario cliente = agendamentoGateway.buscarClientePorId(command.getFkCliente());
        Usuario funcionario = agendamentoGateway.buscarFuncionarioPorId(command.getFkFuncionario());
        Servico servico = agendamentoGateway.buscarServicoPorId(command.getFkServico());
        Pacote pacote = null;
        if (command.getFkPacote() != null) {
            pacote = agendamentoGateway.buscarPacotePorId(command.getFkPacote());
        }

        Agendamento agendamento = new Agendamento(
                null,
                command.getDtHora(),
                command.getValorPago(),
                command.getStatus(),
                command.getStatusAgendamento(),
                cliente,
                funcionario,
                servico,
                pacote
        );

        return agendamentoGateway.salvar(agendamento);
    }
}
