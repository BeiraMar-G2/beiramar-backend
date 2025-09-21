package com.beiramar.beiramar.api.infrastructure.di;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.application.usecase.usuariousecase.*;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaAdapter;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import com.beiramar.beiramar.api.repository.FilesEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsuarioBeanConfig {

    @Bean
    public UsuarioGateway usuarioGateway(UsuarioJpaRepository usuarioJpaRepository, CargoJpaRepository cargoJpaRepository) {
        return new UsuarioJpaAdapter(usuarioJpaRepository, cargoJpaRepository);
    }


    @Bean
    public CadastrarUsuarioUseCase cadastrarUsuarioUseCase(UsuarioGateway usuarioGateway, PasswordEncoder passwordEncoder) {
        return new CadastrarUsuarioUseCase(usuarioGateway, passwordEncoder);
    }

    @Bean
    public AtualizarUsuarioUseCase atualizarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new AtualizarUsuarioUseCase(usuarioGateway);
    }

    @Bean
    public BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase(UsuarioGateway usuarioGateway) {
        return new BuscarUsuarioPorIdUseCase(usuarioGateway);
    }

    @Bean
    public DeletarUsuarioUseCase deletarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new DeletarUsuarioUseCase(usuarioGateway);
    }

    @Bean
    public ListarUsuariosPorCargoUseCase listarUsuariosPorCargoUseCase(UsuarioGateway usuarioGateway) {
        return new ListarUsuariosPorCargoUseCase(usuarioGateway);
    }

    @Bean
    public ListarUsuariosUseCase listarUsuariosUseCase(UsuarioGateway usuarioGateway, FilesEntityRepository filesEntityRepository) {
        return new ListarUsuariosUseCase(usuarioGateway, filesEntityRepository);
    }

    @Bean
    public AtualizarFotoUsuarioUseCase atualizarFotoUsuarioUseCase(UsuarioGateway usuarioGateway) {
        return new AtualizarFotoUsuarioUseCase(usuarioGateway);
    }
}
