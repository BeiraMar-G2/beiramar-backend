package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteCadastroDto;
import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteListagemDto;
import com.beiramar.beiramar.api.entity.Pacote;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.entity.ValorPacote;

public class ValorPacoteMapper {

    public static ValorPacote toEntity(ValorPacoteCadastroDto dto, Usuario usuario, Pacote pacote){

        ValorPacote valorPacote = new ValorPacote();
        valorPacote.setUsuario(usuario);
        valorPacote.setPacote(pacote);
        valorPacote.setValorTotal(dto.getValorTotal());
        return valorPacote;
    }

    public static ValorPacoteListagemDto toDto(ValorPacote valorPacote){
        return new ValorPacoteListagemDto(
                valorPacote.getIdValorPacote(),
                valorPacote.getValorTotal(),
                valorPacote.getUsuario().getNome(),
                valorPacote.getPacote().getNome()
        );
    }

    public static void AtualizarEntity(ValorPacote valorPacote, ValorPacoteAtualizacaoDto dto){
        valorPacote.setValorTotal(dto.getValorTotal());
    }
}
