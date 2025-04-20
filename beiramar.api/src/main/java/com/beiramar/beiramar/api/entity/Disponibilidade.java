package com.beiramar.beiramar.api.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Disponibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDisponibilidade;

    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private LocalDate diaMes;
    private Boolean tipo;

    @ManyToOne
    @JoinColumn(name = "fkFuncionario")
    private Usuario funcionario;

    @ManyToOne
    @JoinColumn(name = "fkFuncExcecao")
    private Usuario funcionarioExcecao;


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

    public LocalDate getDiaMes() {
        return diaMes;
    }

    public void setDiaMes(LocalDate diaMes) {
        this.diaMes = diaMes;
    }

    public Boolean getTipo() {
        return tipo;
    }

    public void setTipo(Boolean tipo) {
        this.tipo = tipo;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }

    public Usuario getFuncionarioExcecao() {
        return funcionarioExcecao;
    }

    public void setFuncionarioExcecao(Usuario funcionarioExcecao) {
        this.funcionarioExcecao = funcionarioExcecao;
    }
}
