package com.beiramar.beiramar.api.core.application.command.disponibilidadecommand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class DisponibilidadeListagemCommand {

    private Integer idDisponibilidade;

    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String diaMes;
    private String Funcionario;
    private Boolean disponibilidadeExcecao;
    private String fkFuncionarioExcecaoNome;

    public DisponibilidadeListagemCommand(Integer idDisponibilidade, String diaSemana, LocalTime horaInicio, LocalTime horaFim, String diaMes, String funcionario, Boolean disponibilidadeExcecao, String fkFuncionarioExcecaoNome) {
        this.idDisponibilidade = idDisponibilidade;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaMes = diaMes;
        Funcionario = funcionario;
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

    public String getFuncionario() {
        return Funcionario;
    }

    public Boolean getDisponibilidadeExcecao() {
        return disponibilidadeExcecao;
    }

    public String getFkFuncionarioExcecaoNome() {
        return fkFuncionarioExcecaoNome;
    }
}
