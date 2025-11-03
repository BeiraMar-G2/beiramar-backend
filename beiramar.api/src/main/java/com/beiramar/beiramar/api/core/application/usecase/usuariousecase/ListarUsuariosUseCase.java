package com.beiramar.beiramar.api.core.application.usecase.usuariousecase;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.application.command.usuariocommand.UsuarioListagemCommand;
import com.beiramar.beiramar.api.core.domain.Usuario;
import com.beiramar.beiramar.api.infrastructure.features.entity.FilesEntity;
import com.beiramar.beiramar.api.infrastructure.features.repository.FilesEntityRepository;

import java.util.List;

public class ListarUsuariosUseCase {

    private final UsuarioGateway gateway;
    private final FilesEntityRepository filesEntityRepository;
    private final String bucketBaseUrl = "https://meu-bucket.s3.amazonaws.com/";

    public ListarUsuariosUseCase(UsuarioGateway gateway, FilesEntityRepository filesEntityRepository) {
        this.gateway = gateway;
        this.filesEntityRepository = filesEntityRepository;
    }

    public List<UsuarioListagemCommand> executar() {
        List<Usuario> usuarios = gateway.listarTodos();

        return usuarios.stream()
                .map(usuario -> {
                    String fotoUrl = null;
                    if (usuario.getFotoPerfilId() != null) {
                        FilesEntity file = filesEntityRepository.findById(usuario.getFotoPerfilId().intValue())
                                .orElse(null);
                        if (file != null) {
                            fotoUrl = bucketBaseUrl + file.getStoredName();
                        }
                    }
                    return new UsuarioListagemCommand(
                            usuario.getIdUsuario(),
                            usuario.getNome(),
                            usuario.getEmail(),
                            usuario.getTelefone(),
                            usuario.getCargo().getNome(),
                            fotoUrl
                    );
                })
                .toList();
    }
}
