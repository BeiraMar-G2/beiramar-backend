package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.ClienteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.ClienteCadastroDto;
import com.beiramar.beiramar.api.dto.ClienteListagemDto;
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
                usuario.getTelefone()
        );
    }

    public static void atualizarEntity(Usuario usuario, ClienteAtualizacaoDto dto) {
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setDtNasc(dto.getDtNasc());
    }
}
