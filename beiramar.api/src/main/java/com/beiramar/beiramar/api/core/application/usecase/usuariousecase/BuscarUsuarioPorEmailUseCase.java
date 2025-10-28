package com.beiramar.beiramar.api.core.application.usecase.usuariousecase;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Usuario;

public class BuscarUsuarioPorEmailUseCase {

    private final UsuarioGateway usuarioGateway;

    public BuscarUsuarioPorEmailUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario executar(String email) {
        return usuarioGateway.buscarPorEmail(email)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuario não encontrado"));
    }
}
