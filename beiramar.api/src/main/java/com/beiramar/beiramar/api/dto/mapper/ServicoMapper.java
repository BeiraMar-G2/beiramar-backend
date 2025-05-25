package com.beiramar.beiramar.api.dto.mapper;


import com.beiramar.beiramar.api.dto.servicoDtos.ServicoCadastroDto;
import com.beiramar.beiramar.api.dto.servicoDtos.ServicoListagemDto;
import com.beiramar.beiramar.api.entity.Servico;

public class ServicoMapper {

    public static Servico toEntity(ServicoCadastroDto dto) {
        Servico servico = new Servico();
        servico.setNome(dto.getNome());
        servico.setPreco(dto.getPreco());
        servico.setDescricao(dto.getDescricao());
        servico.setDuracao(dto.getDuracao());
        return servico;
    }

    public static ServicoListagemDto toDto(Servico entity) {
        ServicoListagemDto dto = new ServicoListagemDto();
        dto.setIdServico(entity.getIdServico());
        dto.setNome(entity.getNome());
        dto.setPreco(entity.getPreco());
        dto.setDescricao(entity.getDescricao());
        dto.setDuracao(entity.getDuracao());
        return dto;
    }
}
