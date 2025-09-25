package com.beiramar.beiramar.api.infrastructure.di;

import com.beiramar.beiramar.api.core.adapter.DisponibilidadeGateway;
import com.beiramar.beiramar.api.core.application.usecase.disponibilidadeusecase.*;
import com.beiramar.beiramar.api.infrastructure.persistence.disponibilidadepersistence.DisponibilidadeJpaAdapter;
import com.beiramar.beiramar.api.infrastructure.persistence.disponibilidadepersistence.DisponibilidadeJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisponibilidadeBeanConfig {

    @Bean
    public DisponibilidadeGateway disponibilidadeGateway(
            DisponibilidadeJpaRepository disponibilidadeJpaRepository,
            UsuarioJpaRepository usuarioRepository
    ) {
        return new DisponibilidadeJpaAdapter(disponibilidadeJpaRepository, usuarioRepository);
    }

    @Bean
    public CadastrarDisponibilidadeUseCase cadastrarDisponibilidadeUseCase(DisponibilidadeGateway gateway) {
        return new CadastrarDisponibilidadeUseCase(gateway);
    }

    @Bean
    public AtualizarDisponibilidadeUseCase atualizarDisponibilidadeUseCase(DisponibilidadeGateway gateway) {
        return new AtualizarDisponibilidadeUseCase(gateway);
    }

    @Bean
    public BuscarDisponibilidadePorIdUseCase buscarDisponibilidadePorIdUseCase(DisponibilidadeGateway gateway) {
        return new BuscarDisponibilidadePorIdUseCase(gateway);
    }

    @Bean
    public DeletarDisponibilidadeUseCase deletarDisponibilidadeUseCase(DisponibilidadeGateway gateway) {
        return new DeletarDisponibilidadeUseCase(gateway);
    }

    @Bean
    public ListarDisponibilidadesUseCase listarDisponibilidadesUseCase(DisponibilidadeGateway gateway) {
        return new ListarDisponibilidadesUseCase(gateway);
    }
}
