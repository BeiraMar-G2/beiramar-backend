package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteCadastroDto;
import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteListagemDto;
import com.beiramar.beiramar.api.entity.Pacote;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.entity.ValorPacoteEntity;

public class ValorPacoteMapper {

    public static ValorPacoteEntity toEntity(ValorPacoteCadastroDto dto, UsuarioEntity usuario, Pacote pacote){

        ValorPacoteEntity valorPacote = new ValorPacoteEntity();
        valorPacote.setUsuario(usuario);
        valorPacote.setPacote(pacote);
        valorPacote.setValorTotal(dto.getValorTotal());
        return valorPacote;
    }

    public static ValorPacoteListagemDto toDto(ValorPacoteEntity valorPacote){
        return new ValorPacoteListagemDto(
                valorPacote.getIdValorPacote(),
                valorPacote.getValorTotal(),
                valorPacote.getUsuario().getNome(),
                valorPacote.getPacote().getNome()
        );
    }

    public static void AtualizarEntity(ValorPacoteEntity valorPacote, ValorPacoteAtualizacaoDto dto){
        valorPacote.setValorTotal(dto.getValorTotal());
    }
}
