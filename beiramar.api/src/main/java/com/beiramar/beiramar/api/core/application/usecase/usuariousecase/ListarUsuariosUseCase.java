package com.beiramar.beiramar.api.core.application.usecase.usuariousecase;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.domain.Usuario;

import java.util.List;

public class ListarUsuariosUseCase {

    private final UsuarioGateway gateway;

    public ListarUsuariosUseCase(UsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public List<Usuario> executar(){
        return gateway.listarTodos();

    }
}
