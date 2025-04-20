package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.FuncionarioListagemDto;
import com.beiramar.beiramar.api.entity.Cargo;
import com.beiramar.beiramar.api.entity.Usuario;

public class FuncionarioMapper {

    public static Usuario toEntity(FuncionarioCadastroDto dto, Cargo cargo) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setSenha(dto.getSenha());
        usuario.setDtNasc(dto.getDtNasc());
        usuario.setCargo(cargo);
        return usuario;
    }

    public static FuncionarioListagemDto toDto(Usuario usuario) {
        return new FuncionarioListagemDto(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone()
        );
    }
}
