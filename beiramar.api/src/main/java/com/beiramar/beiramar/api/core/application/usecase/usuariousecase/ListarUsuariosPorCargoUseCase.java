package com.beiramar.beiramar.api.core.application.usecase.usuariousecase;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Usuario;

import java.util.List;

public class ListarUsuariosPorCargoUseCase {

    private final UsuarioGateway usuarioGateway;

    public ListarUsuariosPorCargoUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public List<Usuario> executar(String nomeCargo) {
        List<Usuario> usuarios = usuarioGateway.listarPorCargo(nomeCargo);

        if (usuarios.isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhum usuario com o cargo: " + nomeCargo);
        }

        return usuarios;
    }
}
