package com.beiramar.beiramar.api.infrastructure.features.dto.mapper;


import com.beiramar.beiramar.api.infrastructure.features.dto.servicoDtos.ServicoCadastroDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.servicoDtos.ServicoListagemDto;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoEntity;

public class ServicoMapper {

    public static ServicoEntity toEntity(ServicoCadastroDto dto) {
        ServicoEntity servico = new ServicoEntity();
        servico.setNome(dto.getNome());
        servico.setPreco(dto.getPreco());
        servico.setDescricao(dto.getDescricao());
        servico.setDuracao(dto.getDuracao());
        return servico;
    }

    public static ServicoListagemDto toDto(ServicoEntity servico) {
        ServicoListagemDto dto = new ServicoListagemDto(
                servico.getIdServico(),
                servico.getNome(),
                servico.getPreco(),
                servico.getDescricao(),
                servico.getDuracao()
        );
        return dto;
    }
}
