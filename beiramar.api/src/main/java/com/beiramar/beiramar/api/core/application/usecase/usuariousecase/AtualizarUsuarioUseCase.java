package com.beiramar.beiramar.api.core.application.usecase.usuariousecase;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.application.command.usuariocommand.UsuarioAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Usuario;

public class AtualizarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public AtualizarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario executar(Integer id, UsuarioAtualizacaoCommand command) {
        Usuario usuario = usuarioGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
        usuario.atualizar(command.getNome(), command.getEmail(), command.getTelefone(), command.getDtNasc());
        return usuarioGateway.salvar(usuario);
    }
}
