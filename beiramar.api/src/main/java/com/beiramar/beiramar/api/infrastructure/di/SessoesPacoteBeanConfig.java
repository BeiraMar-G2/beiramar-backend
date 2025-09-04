package com.beiramar.beiramar.api.infrastructure.di;

import com.beiramar.beiramar.api.core.adapter.SessoesPacoteGateway;
import com.beiramar.beiramar.api.core.application.usecase.sessoespacoteusecase.*;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.sessoespacotepersistence.SessoesPacoteJpaAdapter;
import com.beiramar.beiramar.api.infrastructure.persistence.sessoespacotepersistence.SessoesPacoteJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessoesPacoteBeanConfig {

    @Bean
    public SessoesPacoteGateway sessoesPacoteGateway(SessoesPacoteJpaRepository sessoesPacoteJpaRepository, PacoteJpaRepository pacoteJpaRepository, ServicoJpaRepository servicoJpaRepository){
        return new SessoesPacoteJpaAdapter(sessoesPacoteJpaRepository, pacoteJpaRepository, servicoJpaRepository);
    }

    @Bean
    public CadastrarSessoesPacoteUseCase cadastrarSessoesPacoteUseCase(SessoesPacoteGateway gateway) {
        return new CadastrarSessoesPacoteUseCase(gateway);
    }

    @Bean
    public AtualizarSessoesPacoteUseCase atualizarSessoesPacoteUseCase(SessoesPacoteGateway gateway) {
        return new AtualizarSessoesPacoteUseCase(gateway);
    }

    @Bean
    public BuscarSessoesPacotePorIdUseCase buscarSessoesPacotePorIdUseCase(SessoesPacoteGateway gateway) {
        return new BuscarSessoesPacotePorIdUseCase(gateway);
    }

    @Bean
    public ListarSessoesPacoteUseCase listarSessoesPacoteUseCase(SessoesPacoteGateway gateway) {
        return new ListarSessoesPacoteUseCase(gateway);
    }

    @Bean
    public DeletarSessoesPacoteUseCase deletarSessoesPacoteUseCase(SessoesPacoteGateway gateway) {
        return new DeletarSessoesPacoteUseCase(gateway);
    }
}
