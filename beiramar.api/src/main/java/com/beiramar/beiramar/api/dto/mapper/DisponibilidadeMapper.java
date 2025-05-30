package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.disponibilidadeDtos.DisponibilidadeAtualizacaoDto;
import com.beiramar.beiramar.api.dto.disponibilidadeDtos.DisponibilidadeCadastroDto;
import com.beiramar.beiramar.api.dto.disponibilidadeDtos.DisponibilidadeListagemDto;
import com.beiramar.beiramar.api.entity.Disponibilidade;
import com.beiramar.beiramar.api.entity.Usuario;

public class DisponibilidadeMapper {

    public static Disponibilidade toEntity(DisponibilidadeCadastroDto dto, Usuario funcionario){

        Disponibilidade disponibilidade = new Disponibilidade();
        disponibilidade.setFuncionario(funcionario);
        disponibilidade.setDiaSemana(dto.getDiaSemana());
        disponibilidade.setHoraInicio(dto.getHoraInicio());
        disponibilidade.setHoraFim(dto.getHoraFim());
        disponibilidade.setDiaMes(dto.getDiaMes());
        disponibilidade.setDisponibilidadeExcecao(dto.getDisponibilidadeExcecao());
        disponibilidade.setFkFuncionarioExcecaoNome(dto.getFkFuncionarioExcecaoNome());
        return disponibilidade;
    }

    public static DisponibilidadeListagemDto toDto(Disponibilidade disponibilidade){
        return new DisponibilidadeListagemDto(
                disponibilidade.getIdDisponibilidade(),
                disponibilidade.getDiaSemana(),
                disponibilidade.getHoraInicio(),
                disponibilidade.getHoraFim(),
                disponibilidade.getDiaMes(),
                disponibilidade.getFuncionario().getNome(),
                disponibilidade.getDisponibilidadeExcecao(),
                disponibilidade.getFkFuncionarioExcecaoNome()
        );
    }

    public static void AtualizarEntity(Disponibilidade disponibilidade, DisponibilidadeAtualizacaoDto dto){
        disponibilidade.setDiaSemana(dto.getDiaSemana());
        disponibilidade.setHoraInicio(dto.getHoraInicio());
        disponibilidade.setHoraFim(dto.getHoraFim());
        disponibilidade.setDiaMes(dto.getDiaMes());
        disponibilidade.setDisponibilidadeExcecao(dto.getDisponibilidadeExcecao());
        disponibilidade.setFkFuncionarioExcecaoNome(dto.getFkFuncionarioExcecaoNome());

    }
}
