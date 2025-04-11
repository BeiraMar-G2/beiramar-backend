package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.FuncionarioListagemDto;
import com.beiramar.beiramar.api.entity.Usuario;

public class FuncionarioMapper {

    public static Usuario toUsuario(FuncionarioCadastroDto request) {
        Usuario funcionario = new Usuario();

        funcionario.setNome(request.getNome());
        funcionario.setCpf(request.getCpf());
        funcionario.setEmail(request.getEmail());
        funcionario.setTelefone(request.getTelefone());
        funcionario.setCargo(request.getCargo());
        funcionario.setDtNasc(request.getDtNasc());
        funcionario.setCep(request.getCep());
        funcionario.setLogradouro(request.getLogradouro());
        funcionario.setNumeroLogradouro(request.getNumeroLogradouro());
        funcionario.setSenha(request.getSenha());

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
        response.setCep(funcionario.getCep());
        response.setLogradouro(funcionario.getLogradouro());
        response.setNumeroLogradouro(funcionario.getNumeroLogradouro());
        response.setSenha(funcionario.getSenha());

        return response;
    }
}
