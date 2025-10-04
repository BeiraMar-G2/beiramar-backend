package com.beiramar.beiramar.api.core.application.usecase.usuariousecase;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Usuario;

public class AtualizarFotoUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public AtualizarFotoUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario executar(Integer idUsuario, Integer fotoId) {
        Usuario usuario = usuarioGateway.buscarPorId(idUsuario)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        if (usuario.getFotoPerfilId() == null) {
            // criando
            usuario.atualizarFotoPerfil(fotoId);
        } else {
            // atualizando
            usuario.atualizarFotoPerfil(fotoId);
        }

        return usuarioGateway.salvar(usuario);
    }

}
