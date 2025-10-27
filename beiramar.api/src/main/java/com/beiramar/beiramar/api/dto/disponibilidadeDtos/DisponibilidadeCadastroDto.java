package com.beiramar.beiramar.api.dto.disponibilidadeDtos;

import com.beiramar.beiramar.api.infrastructure.persistence.disponibilidadepersistence.DisponibilidadeEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class DisponibilidadeCadastroDto {

    @NotBlank
    private String diaSemana;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFim;

    @NotBlank
    private String diaMes;

    @NotNull
    private Integer idFuncionario;


    private DisponibilidadeEntity disponibilidadeExcecao;
    private String fkFuncionarioExcecaoNome;

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

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public DisponibilidadeEntity getDisponibilidadeExcecao() {
        return disponibilidadeExcecao;
    }

    public void setDisponibilidadeExcecao(DisponibilidadeEntity disponibilidadeExcecao) {
        this.disponibilidadeExcecao = disponibilidadeExcecao;
    }

    public String getFkFuncionarioExcecaoNome() {
        return fkFuncionarioExcecaoNome;
    }

    public void setFkFuncionarioExcecaoNome(String fkFuncionarioExcecaoNome) {
        this.fkFuncionarioExcecaoNome = fkFuncionarioExcecaoNome;
    }
}
