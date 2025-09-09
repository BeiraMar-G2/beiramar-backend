package com.beiramar.beiramar.api.core.application.usecase.usuariousecase;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Usuario;

import java.util.List;

public class ListarUsuariosUseCase {

    private final UsuarioGateway gateway;

    public ListarUsuariosUseCase(UsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public List<Usuario> executar(){
        List<Usuario> usuarios = gateway.listarTodos();

        if (usuarios.isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhum Usuario cadastrado");
        }
        return usuarios;
    }
}
