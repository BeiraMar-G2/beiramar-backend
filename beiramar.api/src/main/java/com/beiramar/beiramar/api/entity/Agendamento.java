package com.beiramar.beiramar.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Schema(description = "Agendamento da consulta")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgendamento;

    private LocalDateTime dtHora;
    private String status;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento statusAgendamento;

    @ManyToOne
    @JoinColumn(name = "fkCliente")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "fkFuncionario")
    private Usuario funcionario;

    @ManyToOne
    @JoinColumn(name = "fkServico")
    private Servico servico;

    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL)
    private List<LogStatus> logsStatus;

    public Integer getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Integer idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public LocalDateTime getDtHora() {
        return dtHora;
    }

    public void setDtHora(LocalDateTime dtHora) {
        this.dtHora = dtHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StatusAgendamento getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(StatusAgendamento statusAgendamento) {
        this.statusAgendamento = statusAgendamento;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public List<LogStatus> getLogsStatus() {
        return logsStatus;
    }

    public void setLogsStatus(List<LogStatus> logsStatus) {
        this.logsStatus = logsStatus;
    }
}
