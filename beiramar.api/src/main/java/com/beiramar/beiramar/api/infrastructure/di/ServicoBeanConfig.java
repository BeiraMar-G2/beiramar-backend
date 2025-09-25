package com.beiramar.beiramar.api.infrastructure.di;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.usecase.servicousecase.*;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoJpaAdapter;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicoBeanConfig {

    @Bean
    public ServicoGateway servicoGateway(ServicoJpaRepository servicoJpaRepository) {
        return new ServicoJpaAdapter(servicoJpaRepository);
    }

    @Bean
    public CadastrarServicoUseCase cadastrarServicoUseCase(ServicoGateway servicoGateway) {
        return new CadastrarServicoUseCase(servicoGateway);
    }

    @Bean
    public ListarServicosUseCase listarServicosUseCase(ServicoGateway servicoGateway) {
        return new ListarServicosUseCase(servicoGateway);
    }

    @Bean
    public BuscarServicoPorIdUseCase buscarServicoPorIdUseCase(ServicoGateway servicoGateway) {
        return new BuscarServicoPorIdUseCase(servicoGateway);
    }

    @Bean
    public AtualizarServicoUseCase atualizarServicoUseCase(ServicoGateway servicoGateway) {
        return new AtualizarServicoUseCase(servicoGateway);
    }

    @Bean
    public DeletarServicoUseCase deletarServicoUseCase(ServicoGateway servicoGateway) {
        return new DeletarServicoUseCase(servicoGateway);
    }

    @Bean
    public BuscarTop3ServicosMaisAgendadosUseCase buscarTop3ServicosMaisAgendadosUseCase(ServicoGateway servicoGateway) {
        return new BuscarTop3ServicosMaisAgendadosUseCase(servicoGateway);
    }

    @Bean
    public BuscarTop3ServicosMenosAgendadosUseCase buscarTop3ServicosMenosAgendadosUseCase(ServicoGateway servicoGateway) {
        return new BuscarTop3ServicosMenosAgendadosUseCase(servicoGateway);
    }
}
