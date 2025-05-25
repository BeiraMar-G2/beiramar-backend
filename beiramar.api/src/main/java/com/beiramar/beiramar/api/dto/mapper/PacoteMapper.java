package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteCadastroDto;
import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteListagemDto;
import com.beiramar.beiramar.api.entity.Pacote;

public class PacoteMapper {

    public static Pacote toEntity(PacoteCadastroDto dto) {
        Pacote pacote = new Pacote();
        pacote.setNome(dto.getNome());
        pacote.setPrecoTotalSemDesconto(dto.getPrecoTotalSemDesconto());
        pacote.setQtdSessoesTotal(dto.getQtdSessoesTotal());
        pacote.setTempoLimiteDias(dto.getTempoLimiteDias());
        return pacote;
    }

    public static PacoteListagemDto toDto(Pacote pacote) {
        return new PacoteListagemDto(
                pacote.getIdPacote(),
                pacote.getNome(),
                pacote.getPrecoTotalSemDesconto(),
                pacote.getQtdSessoesTotal(),
                pacote.getTempoLimiteDias()
        );
    }

    public static void AtualizarEntity(Pacote pacote, PacoteAtualizacaoDto dto) {
        pacote.setNome(dto.getNome());
        pacote.setPrecoTotalSemDesconto(dto.getPrecoTotalSemDesconto());
        pacote.setQtdSessoesTotal(dto.getQtdSessoesTotal());
        pacote.setTempoLimiteDias(dto.getTempoLimiteDias());
    }
}
