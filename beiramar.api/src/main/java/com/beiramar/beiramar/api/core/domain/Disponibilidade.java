package com.beiramar.beiramar.api.core.domain;

import java.time.LocalTime;

public class Disponibilidade {

    private Integer idDisponibilidade;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String diaMes;
    private Usuario funcionario;
    private Boolean disponibilidadeExcecao;
    private String fkFuncionarioExcecaoNome;

    public Disponibilidade(Integer idDisponibilidade, String diaSemana, LocalTime horaInicio, LocalTime horaFim, String diaMes, Usuario funcionario, Boolean disponibilidadeExcecao, String fkFuncionarioExcecaoNome) {
        this.idDisponibilidade = idDisponibilidade;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaMes = diaMes;
        this.funcionario = funcionario;
        this.disponibilidadeExcecao = disponibilidadeExcecao;
        this.fkFuncionarioExcecaoNome = fkFuncionarioExcecaoNome;
    }

    public Disponibilidade(String diaSemana, LocalTime horaInicio, LocalTime horaFim, String diaMes, Usuario funcionario, Boolean disponibilidadeExcecao, String fkFuncionarioExcecaoNome) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaMes = diaMes;
        this.funcionario = funcionario;
        this.disponibilidadeExcecao = disponibilidadeExcecao;
        this.fkFuncionarioExcecaoNome = fkFuncionarioExcecaoNome;
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

    public Boolean getDisponibilidadeExcecao() {
        return disponibilidadeExcecao;
    }

    public String getFkFuncionarioExcecaoNome() {
        return fkFuncionarioExcecaoNome;
    }

    public void atualizar(String diaSemana, LocalTime horaInicio, LocalTime horaFim, String diaMes, Boolean disponibilidadeExcecao, String fkFuncionarioExcecaoNome){
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaMes = diaMes;
        this.disponibilidadeExcecao = disponibilidadeExcecao;
        this.fkFuncionarioExcecaoNome = fkFuncionarioExcecaoNome;
    }
}
