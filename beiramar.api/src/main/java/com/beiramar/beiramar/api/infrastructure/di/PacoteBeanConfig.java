package com.beiramar.beiramar.api.infrastructure.di;

import com.beiramar.beiramar.api.core.adapter.PacoteGateway;
import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.usecase.pacoteusecase.*;
import com.beiramar.beiramar.api.core.application.usecase.servicousecase.*;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaAdapter;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoJpaAdapter;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PacoteBeanConfig {

    @Bean
    public PacoteGateway pacoteGateway(PacoteJpaRepository pacoteJpaRepository) {
        return new PacoteJpaAdapter(pacoteJpaRepository);
    }

    @Bean
    public CadastrarPacoteUseCase cadastrarPacoteUseCaseUseCase(PacoteGateway pacoteGateway) {
        return new CadastrarPacoteUseCase(pacoteGateway);
    }

    @Bean
    public ListarPacotesUseCase listarPacotesUseCase(PacoteGateway pacoteGateway) {
        return new ListarPacotesUseCase(pacoteGateway);
    }

    @Bean
    public BuscarPacotePorIdUseCase buscarPacotePorIdUseCase(PacoteGateway pacoteGateway) {
        return new BuscarPacotePorIdUseCase(pacoteGateway);
    }

    @Bean
    public AtualizarPacoteUseCase atualizarPacoteUseCase(PacoteGateway pacoteGateway) {
        return new AtualizarPacoteUseCase(pacoteGateway);
    }

    @Bean
    public DeletarPacoteUseCase deletarPacoteUseCase(PacoteGateway pacoteGateway) {
        return new DeletarPacoteUseCase(pacoteGateway);
    }
}
