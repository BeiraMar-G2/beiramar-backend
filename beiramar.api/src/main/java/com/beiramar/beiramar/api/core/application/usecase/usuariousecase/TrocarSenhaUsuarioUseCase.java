package com.beiramar.beiramar.api.core.application.usecase.usuariousecase;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.application.command.usuariocommand.UsuarioTrocarSenhaCommand;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TrocarSenhaUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final PasswordEncoder passwordEncoder;

    public TrocarSenhaUsuarioUseCase(UsuarioGateway usuarioGateway, PasswordEncoder passwordEncoder) {
        this.usuarioGateway = usuarioGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public void executar(Integer idUsuario, UsuarioTrocarSenhaCommand command) {
        Usuario usuario = usuarioGateway.buscarPorId(idUsuario)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado."));

        // Verifica senha atual
        if (!passwordEncoder.matches(command.getSenhaAtual(), usuario.getSenha())) {
            throw new IllegalArgumentException("Senha atual incorreta.");
        }

        // Verifica se nova e confirmação coincidem
        if (!command.getNovaSenha().equals(command.getConfirmarNovaSenha())) {
            throw new IllegalArgumentException("A nova senha e a confirmação não coincidem.");
        }

        // Evita reutilização da mesma senha
        if (command.getSenhaAtual().equals(command.getNovaSenha())) {
            throw new IllegalArgumentException("A nova senha não pode ser igual à atual.");
        }

        // Atualiza senha
        usuario.atualizarSenha(passwordEncoder.encode(command.getNovaSenha()));
        usuarioGateway.salvar(usuario);
    }
}
