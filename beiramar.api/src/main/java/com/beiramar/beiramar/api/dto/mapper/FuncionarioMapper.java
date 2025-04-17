package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.FuncionarioListagemDto;
import com.beiramar.beiramar.api.entity.TipoUsuarioEnum;
import com.beiramar.beiramar.api.entity.Usuario;

public class FuncionarioMapper {

    public static Usuario toFuncionarioDto(FuncionarioCadastroDto request) {
        Usuario funcionario = new Usuario();

        funcionario.setNome(request.getNome());
        funcionario.setCpf(request.getCpf());
        funcionario.setEmail(request.getEmail());
        funcionario.setTelefone(request.getTelefone());
        funcionario.setCargo(request.getCargo());
        funcionario.setDtNasc(request.getDtNasc());
        funcionario.setSenha(request.getSenha());
        funcionario.setTipoUsuario(TipoUsuarioEnum.FUNCIONARIO); // novo

        return funcionario;
    }

    public static FuncionarioListagemDto toFuncionarioListagemDto(Usuario funcionario){
        FuncionarioListagemDto response = new FuncionarioListagemDto();

        response.setIdFuncionario(funcionario.getIdPessoa());
        response.setNome(funcionario.getNome());
        response.setCpf(funcionario.getCpf());
        response.setEmail(funcionario.getEmail());
        response.setTelefone(funcionario.getTelefone());
        response.setCargo(funcionario.getCargo());
        response.setDtNasc(funcionario.getDtNasc());
        response.setSenha(funcionario.getSenha());

        return response;
    }
}
