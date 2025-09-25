package com.beiramar.beiramar.api.core.application.command.disponibilidadecommand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class DisponibilidadeAtualizacaoCommand {

    @NotBlank
    private String diaSemana;
    @NotNull
    private LocalTime horaInicio;
    @NotNull
    private LocalTime horaFim;
    @NotBlank
    private String diaMes;

    private Boolean disponibilidadeExcecao;
    @NotNull
    private String fkFuncionarioExcecaoNome;

    public DisponibilidadeAtualizacaoCommand(String diaSemana, LocalTime horaInicio, LocalTime horaFim, String diaMes, Boolean disponibilidadeExcecao, String fkFuncionarioExcecaoNome) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaMes = diaMes;
        this.disponibilidadeExcecao = disponibilidadeExcecao;
        this.fkFuncionarioExcecaoNome = fkFuncionarioExcecaoNome;
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

    public Boolean getDisponibilidadeExcecao() {
        return disponibilidadeExcecao;
    }

    public String getFkFuncionarioExcecaoNome() {
        return fkFuncionarioExcecaoNome;
    }
}
