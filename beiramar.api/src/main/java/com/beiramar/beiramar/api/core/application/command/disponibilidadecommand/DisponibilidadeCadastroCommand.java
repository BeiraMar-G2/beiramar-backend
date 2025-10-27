package com.beiramar.beiramar.api.core.application.command.disponibilidadecommand;

import com.beiramar.beiramar.api.core.domain.Usuario;
import com.beiramar.beiramar.api.infrastructure.persistence.disponibilidadepersistence.DisponibilidadeEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class DisponibilidadeCadastroCommand {

    @NotBlank
    private String diaSemana;
    @NotNull
    private LocalTime horaInicio;
    @NotNull
    private LocalTime horaFim;
    @NotBlank
    private String diaMes;
    @NotNull
    private Integer fkFuncionario;

    private DisponibilidadeEntity disponibilidadeExcecao;
    @NotNull
    private String fkFuncionarioExcecaoNome;

    public DisponibilidadeCadastroCommand(String diaSemana, LocalTime horaInicio, LocalTime horaFim, String diaMes, Integer fkFuncionario, DisponibilidadeEntity disponibilidadeExcecao, String fkFuncionarioExcecaoNome) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaMes = diaMes;
        this.fkFuncionario = fkFuncionario;
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

    public Integer getFkFuncionario() {
        return fkFuncionario;
    }

    public DisponibilidadeEntity getDisponibilidadeExcecao() {
        return disponibilidadeExcecao;
    }

    public String getFkFuncionarioExcecaoNome() {
        return fkFuncionarioExcecaoNome;
    }
}
