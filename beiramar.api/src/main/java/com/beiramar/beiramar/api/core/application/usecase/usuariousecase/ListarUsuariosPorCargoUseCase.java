package com.beiramar.beiramar.api.core.application.usecase.usuariousecase;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.domain.Usuario;

import java.util.List;

public class ListarUsuariosPorCargoUseCase {

    private final UsuarioGateway usuarioGateway;

    public ListarUsuariosPorCargoUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public List<Usuario> executar(String nomeCargo) {
        return usuarioGateway.listarPorCargo(nomeCargo);
    }
}
