package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.clienteDtos.ClienteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.clienteDtos.ClienteCadastroDto;
import com.beiramar.beiramar.api.dto.clienteDtos.ClienteListagemDto;
import com.beiramar.beiramar.api.entity.Cargo;
import com.beiramar.beiramar.api.entity.Usuario;

public class ClienteMapper {

    public static Usuario toEntity(ClienteCadastroDto dto, Cargo cargo) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setSenha(dto.getSenha());
        usuario.setDtNasc(dto.getDtNasc());
        usuario.setCargo(cargo);
        return usuario;
    }

    public static ClienteListagemDto toDto(Usuario usuario) {
        return new ClienteListagemDto(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getCargo().getNome()
        );
    }

    public static void atualizarEntity(Usuario usuario, ClienteAtualizacaoDto dto) {
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setDtNasc(dto.getDtNasc());
    }
}
