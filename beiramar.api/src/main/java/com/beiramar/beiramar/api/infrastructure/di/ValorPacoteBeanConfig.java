package com.beiramar.beiramar.api.infrastructure.di;

import com.beiramar.beiramar.api.core.adapter.ValorPacoteGateway;
import com.beiramar.beiramar.api.core.application.usecase.valorpacoteusecase.*;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.valorpacotepersistence.ValorPacoteJpaAdapter;
import com.beiramar.beiramar.api.infrastructure.persistence.valorpacotepersistence.ValorPacoteJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValorPacoteBeanConfig {

    @Bean
    public ValorPacoteGateway valorPacoteGateway(ValorPacoteJpaRepository valorPacoteJpaRepository, UsuarioJpaRepository usuarioJpaRepository, PacoteJpaRepository pacoteJpaRepository){
        return new ValorPacoteJpaAdapter(valorPacoteJpaRepository, usuarioJpaRepository, pacoteJpaRepository);
    }

    @Bean
    public CadastrarValorPacoteUseCase cadastrarValorPacoteUseCase(ValorPacoteGateway gateway) {
        return new CadastrarValorPacoteUseCase(gateway);
    }

    @Bean
    public AtualizarValorPacoteUseCase atualizarValorPacoteUseCase(ValorPacoteGateway gateway) {
        return new AtualizarValorPacoteUseCase(gateway);
    }

    @Bean
    public BuscarValorPacotePorIdUseCase buscarValorPacotePorIdUseCase(ValorPacoteGateway gateway) {
        return new BuscarValorPacotePorIdUseCase(gateway);
    }

    @Bean
    public ListarValorPacoteUseCase listarValorPacoteUseCase(ValorPacoteGateway gateway) {
        return new ListarValorPacoteUseCase(gateway);
    }

    @Bean
    public DeletarValorPacoteUseCase deletarValorPacoteUseCase(ValorPacoteGateway gateway) {
        return new DeletarValorPacoteUseCase(gateway);
    }

}
