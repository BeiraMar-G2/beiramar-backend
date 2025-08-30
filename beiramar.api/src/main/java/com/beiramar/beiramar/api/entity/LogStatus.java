package com.beiramar.beiramar.api.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class LogStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLogStatus;

    private String status;
    private LocalDateTime dataAlteracao;

    @ManyToOne
    @JoinColumn(name = "fkAgendamento")
    private AgendamentoEntity agendamento;

    public Integer getIdLogStatus() {
        return idLogStatus;
    }

    public void setIdLogStatus(Integer idLogStatus) {
        this.idLogStatus = idLogStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public AgendamentoEntity getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(AgendamentoEntity agendamento) {
        this.agendamento = agendamento;
    }
}
