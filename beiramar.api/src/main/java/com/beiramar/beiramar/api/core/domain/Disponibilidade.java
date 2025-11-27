package com.beiramar.beiramar.api.core.domain;

import com.beiramar.beiramar.api.infrastructure.persistence.disponibilidadepersistence.DisponibilidadeEntity;

import java.time.LocalTime;

public class Disponibilidade {

    private Integer idDisponibilidade;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String diaMes;
    private Usuario funcionario;
    private DisponibilidadeEntity disponibilidadeExcecao;

    public Disponibilidade(Integer idDisponibilidade, String diaSemana, LocalTime horaInicio, LocalTime horaFim, String diaMes, Usuario funcionario, DisponibilidadeEntity disponibilidadeExcecao) {
        this.idDisponibilidade = idDisponibilidade;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaMes = diaMes;
        this.funcionario = funcionario;
        this.disponibilidadeExcecao = disponibilidadeExcecao;
    }

    public Disponibilidade(String diaSemana, LocalTime horaInicio, LocalTime horaFim, String diaMes, Usuario funcionario, DisponibilidadeEntity disponibilidadeExcecao) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaMes = diaMes;
        this.funcionario = funcionario;
        this.disponibilidadeExcecao = disponibilidadeExcecao;
    }

    public Integer getIdDisponibilidade() {
        return idDisponibilidade;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public String getDiaMes() {
        return diaMes;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public DisponibilidadeEntity getDisponibilidadeExcecao() {
        return disponibilidadeExcecao;
    }


    public void atualizar(String diaSemana, LocalTime horaInicio, LocalTime horaFim, String diaMes, DisponibilidadeEntity disponibilidadeExcecao){
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaMes = diaMes;
        this.disponibilidadeExcecao = disponibilidadeExcecao;
    }
}
