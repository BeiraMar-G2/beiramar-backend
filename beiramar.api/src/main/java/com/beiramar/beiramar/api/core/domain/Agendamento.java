package com.beiramar.beiramar.api.core.domain;

import java.time.LocalDateTime;

public class Agendamento {

    private Integer idAgendamento;
    private LocalDateTime dtHora;
    private Double valorPago;
    private String status;
    private String statusAgendamento;
    private Usuario cliente;
    private Usuario funcionario;
    private Servico servico;
    private Pacote pacote;

    public Agendamento(Integer idAgendamento, LocalDateTime dtHora, Double valorPago, String status, String statusAgendamento, Usuario cliente, Usuario funcionario, Servico servico, Pacote pacote) {
        this.idAgendamento = idAgendamento;
        this.dtHora = dtHora;
        this.valorPago = valorPago;
        this.status = status;
        this.statusAgendamento = statusAgendamento;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.servico = servico;
        this.pacote = pacote;
    }


    public Agendamento(LocalDateTime dtHora, Double valorPago, String status, String statusAgendamento, Usuario cliente, Usuario funcionario, Servico servico, Pacote pacote) {
        this.dtHora = dtHora;
        this.valorPago = valorPago;
        this.status = status;
        this.statusAgendamento = statusAgendamento;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.servico = servico;
        this.pacote = pacote;
    }


    public Integer getIdAgendamento() {
        return idAgendamento;
    }

    public LocalDateTime getDtHora() {
        return dtHora;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusAgendamento() {
        return statusAgendamento;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public Servico getServico() {
        return servico;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void atualizar(LocalDateTime dtHora, Double valorPago, String status, String statusAgendamento) {
        this.dtHora = dtHora;
        this.valorPago = valorPago;
        this.status = status;
        this.statusAgendamento = statusAgendamento;
    }
}
