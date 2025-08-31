package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteCadastroDto;
import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteListagemDto;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;

public class PacoteMapper {

    public static PacoteEntity toEntity(PacoteCadastroDto dto) {
        PacoteEntity pacote = new PacoteEntity();
        pacote.setNome(dto.getNome());
        pacote.setPrecoTotalSemDesconto(dto.getPrecoTotalSemDesconto());
        pacote.setQtdSessoesTotal(dto.getQtdSessoesTotal());
        pacote.setTempoLimiteDias(dto.getTempoLimiteDias());
        return pacote;
    }

    public static PacoteListagemDto toDto(PacoteEntity pacote) {
        return new PacoteListagemDto(
                pacote.getIdPacote(),
                pacote.getNome(),
                pacote.getPrecoTotalSemDesconto(),
                pacote.getQtdSessoesTotal(),
                pacote.getTempoLimiteDias()
        );
    }

    public static void AtualizarEntity(PacoteEntity pacote, PacoteAtualizacaoDto dto) {
        pacote.setNome(dto.getNome());
        pacote.setPrecoTotalSemDesconto(dto.getPrecoTotalSemDesconto());
        pacote.setQtdSessoesTotal(dto.getQtdSessoesTotal());
        pacote.setTempoLimiteDias(dto.getTempoLimiteDias());
    }
}
