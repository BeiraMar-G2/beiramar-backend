package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.command.servicocommand.ServicoCadastroCommand;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Servico;

import java.util.List;

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

    public static class BuscarTop3ServicosMaisAgendados {

        private final ServicoGateway servicoGateway;

        public BuscarTop3ServicosMaisAgendados(ServicoGateway servicoGateway) {
            this.servicoGateway = servicoGateway;
        }

        public List<Object[]> executar() {
            List<Object[]> top3Servicos = servicoGateway.buscarTop3ServicosMaisAgendados();

            if (top3Servicos.isEmpty()) {
                throw new EntidadeNaoEncontradaException("Nenhum serviço encontrado");
            }

            return top3Servicos;
        }
    }
}
