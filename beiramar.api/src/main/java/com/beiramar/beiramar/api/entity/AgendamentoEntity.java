package com.beiramar.beiramar.api.entity;

import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Agendamento")
@Schema(description = "Agendamento da consulta")
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgendamento;

    private LocalDateTime dtHora;
    private Double valorPago;
    private String status;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento statusAgendamento;

    @ManyToOne
    @JoinColumn(name = "fkCliente")
    private UsuarioEntity cliente;

    @ManyToOne
    @JoinColumn(name = "fkFuncionario")
    private UsuarioEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "fkServico")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "fkPacote")
    private Pacote pacote;

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

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
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

    public UsuarioEntity getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioEntity cliente) {
        this.cliente = cliente;
    }

    public UsuarioEntity getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(UsuarioEntity funcionario) {
        this.funcionario = funcionario;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public List<LogStatus> getLogsStatus() {
        return logsStatus;
    }

    public void setLogsStatus(List<LogStatus> logsStatus) {
        this.logsStatus = logsStatus;
    }
}
