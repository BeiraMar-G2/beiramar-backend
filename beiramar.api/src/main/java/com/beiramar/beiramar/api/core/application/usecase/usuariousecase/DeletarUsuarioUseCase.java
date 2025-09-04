package com.beiramar.beiramar.api.core.application.usecase.usuariousecase;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

public class DeletarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public DeletarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public void executar(Integer id) {

        if (!usuarioGateway.existePorId(id)) {
            throw new EntidadeNaoEncontradaException("Usuario não encontrado");
        }
        usuarioGateway.deletar(id);
    }
}
