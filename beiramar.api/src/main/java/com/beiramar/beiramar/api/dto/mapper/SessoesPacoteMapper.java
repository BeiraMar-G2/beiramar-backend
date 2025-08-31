package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteCadastroDto;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteListagemDto;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoEntity;
import com.beiramar.beiramar.api.entity.SessoesPacote;

public class SessoesPacoteMapper {

    public static SessoesPacote toEntity(SessoesPacoteCadastroDto dto, PacoteEntity pacote, ServicoEntity servico){

        SessoesPacote sessoesPacote = new SessoesPacote();
        sessoesPacote.setPacote(pacote);
        sessoesPacote.setServico(servico);
        sessoesPacote.setQtdSessoes(dto.getQtdSessoes());
        return sessoesPacote;
    }

    public static SessoesPacoteListagemDto toDto(SessoesPacote sessoesPacote){
        return new SessoesPacoteListagemDto(
                sessoesPacote.getIdSessoesPacote(),
                sessoesPacote.getPacote().getNome(),
                sessoesPacote.getServico().getNome(),
                sessoesPacote.getQtdSessoes()
        );
    }

    public static void AtualizarEntity(SessoesPacote sessoesPacote, SessoesPacoteAtualizacaoDto dto){
        sessoesPacote.setQtdSessoes(dto.getQtdSessoes());
    }
}
