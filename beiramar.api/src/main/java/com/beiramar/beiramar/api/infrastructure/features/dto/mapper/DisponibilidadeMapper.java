package com.beiramar.beiramar.api.infrastructure.features.dto.mapper;

import com.beiramar.beiramar.api.infrastructure.features.dto.disponibilidadeDtos.DisponibilidadeAtualizacaoDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.disponibilidadeDtos.DisponibilidadeCadastroDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.disponibilidadeDtos.DisponibilidadeListagemDto;
import com.beiramar.beiramar.api.infrastructure.persistence.disponibilidadepersistence.DisponibilidadeEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;

public class DisponibilidadeMapper {

    public static DisponibilidadeEntity toEntity(DisponibilidadeCadastroDto dto, UsuarioEntity funcionario){

        DisponibilidadeEntity disponibilidade = new DisponibilidadeEntity();
        disponibilidade.setFuncionario(funcionario);
        disponibilidade.setDiaSemana(dto.getDiaSemana());
        disponibilidade.setHoraInicio(dto.getHoraInicio());
        disponibilidade.setHoraFim(dto.getHoraFim());
        disponibilidade.setDiaMes(dto.getDiaMes());
        disponibilidade.setDisponibilidadeExcecao(dto.getDisponibilidadeExcecao());
        return disponibilidade;
    }

    public static DisponibilidadeListagemDto toDto(DisponibilidadeEntity disponibilidade){
        return new DisponibilidadeListagemDto(
                disponibilidade.getIdDisponibilidade(),
                disponibilidade.getDiaSemana(),
                disponibilidade.getHoraInicio(),
                disponibilidade.getHoraFim(),
                disponibilidade.getDiaMes(),
                disponibilidade.getFuncionario().getNome(),
                disponibilidade.getDisponibilidadeExcecao()
        );
    }

    public static void AtualizarEntity(DisponibilidadeEntity disponibilidade, DisponibilidadeAtualizacaoDto dto){
        disponibilidade.setDiaSemana(dto.getDiaSemana());
        disponibilidade.setHoraInicio(dto.getHoraInicio());
        disponibilidade.setHoraFim(dto.getHoraFim());
        disponibilidade.setDiaMes(dto.getDiaMes());
        disponibilidade.setDisponibilidadeExcecao(dto.getDisponibilidadeExcecao());

    }
}
