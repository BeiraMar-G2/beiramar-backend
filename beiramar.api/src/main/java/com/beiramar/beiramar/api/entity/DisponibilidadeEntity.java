package com.beiramar.beiramar.api.entity;

import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Schema(description = "Disponibilidade do Funcionário/Cliente")
@Table(schema = "Disponibilidade")
public class DisponibilidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDisponibilidade;

    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String diaMes;

    @ManyToOne
    @JoinColumn(name = "fkFuncionario")
    private UsuarioEntity funcionario;

    private Boolean disponibilidadeExcecao;
    private String fkFuncionarioExcecaoNome;


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

    public UsuarioEntity getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(UsuarioEntity funcionario) {
        this.funcionario = funcionario;
    }

    public Boolean getDisponibilidadeExcecao() {
        return disponibilidadeExcecao;
    }

    public void setDisponibilidadeExcecao(Boolean disponibilidadeExcecao) {
        this.disponibilidadeExcecao = disponibilidadeExcecao;
    }

    public String getFkFuncionarioExcecaoNome() {
        return fkFuncionarioExcecaoNome;
    }

    public void setFkFuncionarioExcecaoNome(String fkFuncionarioExcecaoNome) {
        this.fkFuncionarioExcecaoNome = fkFuncionarioExcecaoNome;
    }
}
