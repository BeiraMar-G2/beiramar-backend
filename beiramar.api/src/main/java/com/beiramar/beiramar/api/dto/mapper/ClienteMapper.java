package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.ClienteCadastroDto;
import com.beiramar.beiramar.api.dto.ClienteListagemDto;
import com.beiramar.beiramar.api.entity.TipoUsuarioEnum;
import com.beiramar.beiramar.api.entity.Usuario;

public class ClienteMapper {

    public static Usuario toClienteDto (ClienteCadastroDto request) {

        Usuario cliente = new Usuario();

        cliente.setNome(request.getNome());
        cliente.setCpf(request.getCpf());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setDtNasc(request.getDtNasc());
        cliente.setSenha(request.getSenha());
        cliente.setTipoUsuario(TipoUsuarioEnum.CLIENTE);

        return cliente;
    }

    public static ClienteListagemDto toClienteListagemDto(Usuario cliente){
        ClienteListagemDto response = new ClienteListagemDto();

        response.setIdCliente(cliente.getIdPessoa());
        response.setNome(cliente.getNome());
        response.setCpf(cliente.getCpf());
        response.setEmail(cliente.getEmail());
        response.setTelefone(cliente.getTelefone());
        response.setDtNasc(cliente.getDtNasc());
        response.setSenha(cliente.getSenha());

        return response;
    }
}
