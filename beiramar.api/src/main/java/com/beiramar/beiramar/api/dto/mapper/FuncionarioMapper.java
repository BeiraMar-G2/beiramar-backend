package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.funcionarioDtos.FuncionarioAtualizacaoDto;
import com.beiramar.beiramar.api.dto.funcionarioDtos.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.funcionarioDtos.FuncionarioListagemDto;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;

public class FuncionarioMapper {

    public static UsuarioEntity toEntity(FuncionarioCadastroDto dto, CargoEntity cargo) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setSenha(dto.getSenha());
        usuario.setDtNasc(dto.getDtNasc());
        usuario.setCargo(cargo);
        return usuario;
    }

    public static FuncionarioListagemDto toDto(UsuarioEntity usuario) {
        return new FuncionarioListagemDto(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getCargo().getNome()
        );
    }

    public static void atualizarEntity(UsuarioEntity usuario, FuncionarioAtualizacaoDto dto) {
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setDtNasc(dto.getDtNasc());
    }
}
