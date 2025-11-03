package com.beiramar.beiramar.api.infrastructure.features.dto.mapper;

import com.beiramar.beiramar.api.infrastructure.features.dto.sessaoPacoteDtos.SessoesPacoteAtualizacaoDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.sessaoPacoteDtos.SessoesPacoteCadastroDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.sessaoPacoteDtos.SessoesPacoteListagemDto;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.sessoespacotepersistence.SessoesPacoteEntity;

public class SessoesPacoteMapper {

    public static SessoesPacoteEntity toEntity(SessoesPacoteCadastroDto dto, PacoteEntity pacote, ServicoEntity servico){

        SessoesPacoteEntity sessoesPacote = new SessoesPacoteEntity();
        sessoesPacote.setPacote(pacote);
        sessoesPacote.setServico(servico);
        sessoesPacote.setQtdSessoes(dto.getQtdSessoes());
        return sessoesPacote;
    }

    public static SessoesPacoteListagemDto toDto(SessoesPacoteEntity sessoesPacote){
        return new SessoesPacoteListagemDto(
                sessoesPacote.getIdSessoesPacote(),
                sessoesPacote.getPacote().getNome(),
                sessoesPacote.getServico().getNome(),
                sessoesPacote.getQtdSessoes()
        );
    }

    public static void AtualizarEntity(SessoesPacoteEntity sessoesPacote, SessoesPacoteAtualizacaoDto dto){
        sessoesPacote.setQtdSessoes(dto.getQtdSessoes());
    }
}
