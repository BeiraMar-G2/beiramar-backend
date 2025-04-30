package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.PacoteCadastroDto;
import com.beiramar.beiramar.api.dto.PacoteListagemDto;
import com.beiramar.beiramar.api.entity.Pacote;

public class PacoteMapper {

    public static Pacote toEntity(PacoteCadastroDto dto) {
        Pacote pacote = new Pacote();
        pacote.setNome(dto.getNome());
        pacote.setPreco(dto.getPreco());
        return pacote;
    }

    public static PacoteListagemDto toDto(Pacote entity) {
        PacoteListagemDto dto = new PacoteListagemDto();
        dto.setIdPacote(entity.getIdPacote());
        dto.setNome(entity.getNome());
        dto.setPreco(entity.getPreco());
        return dto;
    }
}
