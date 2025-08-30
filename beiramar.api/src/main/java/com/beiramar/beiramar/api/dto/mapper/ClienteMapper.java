package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.clienteDtos.ClienteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.clienteDtos.ClienteCadastroDto;
import com.beiramar.beiramar.api.dto.clienteDtos.ClienteListagemDto;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;

public class ClienteMapper {

    public static UsuarioEntity toEntity(ClienteCadastroDto dto, CargoEntity cargo) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setSenha(dto.getSenha());
        usuario.setDtNasc(dto.getDtNasc());
        usuario.setCargo(cargo);
        return usuario;
    }

    public static ClienteListagemDto toDto(UsuarioEntity usuario) {
        return new ClienteListagemDto(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getCargo().getNome()
        );
    }

    public static void atualizarEntity(UsuarioEntity usuario, ClienteAtualizacaoDto dto) {
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setDtNasc(dto.getDtNasc());
    }
}
