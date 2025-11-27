package com.beiramar.beiramar.api.infrastructure.features.dto.disponibilidadeDtos;

import com.beiramar.beiramar.api.infrastructure.persistence.disponibilidadepersistence.DisponibilidadeEntity;

import java.time.LocalTime;

public class DisponibilidadeListagemDto {

    private Integer idDisponibilidade;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String diaMes;
    private String nomeFuncionario;
    private DisponibilidadeEntity disponibilidadeExcecao;

    public DisponibilidadeListagemDto(Integer idDisponibilidade, String diaSemana, LocalTime horaInicio, LocalTime horaFim, String diaMes, String nomeFuncionario, DisponibilidadeEntity disponibilidadeExcecao) {
        this.idDisponibilidade = idDisponibilidade;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaMes = diaMes;
        this.nomeFuncionario = nomeFuncionario;
        this.disponibilidadeExcecao = disponibilidadeExcecao;
    }

    public DisponibilidadeListagemDto() {

    }

    public Integer getIdDisponibilidade() {
        return idDisponibilidade;
    }

    public void setIdDisponibilidade(Integer idDisponibilidade) {
        this.idDisponibilidade = idDisponibilidade;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public String getDiaMes() {
        return diaMes;
    }

    public void setDiaMes(String diaMes) {
        this.diaMes = diaMes;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public DisponibilidadeEntity getDisponibilidadeExcecao() {
        return disponibilidadeExcecao;
    }

    public void setDisponibilidadeExcecao(DisponibilidadeEntity disponibilidadeExcecao) {
        this.disponibilidadeExcecao = disponibilidadeExcecao;
    }
}
