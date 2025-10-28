package com.beiramar.beiramar.api.core.application.usecase.usuariousecase;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.application.command.usuariocommand.UsuarioCadastroCommand;
import com.beiramar.beiramar.api.core.application.exception.DuplicacaoException;
import com.beiramar.beiramar.api.core.domain.Cargo;
import com.beiramar.beiramar.api.core.domain.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CadastrarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final PasswordEncoder passwordEncoder;


    public CadastrarUsuarioUseCase(UsuarioGateway usuarioGateway, PasswordEncoder passwordEncoder) {
        this.usuarioGateway = usuarioGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario executar(UsuarioCadastroCommand command) {

        Cargo cargo = usuarioGateway.buscarCargoPorId(command.getFkCargo());
        Usuario usuario = new Usuario(
                command.getNome(),
                command.getEmail(),
                command.getTelefone(),
                passwordEncoder.encode(command.getSenha()),
                cargo
        );
        return usuarioGateway.salvar(usuario);
    }
}
