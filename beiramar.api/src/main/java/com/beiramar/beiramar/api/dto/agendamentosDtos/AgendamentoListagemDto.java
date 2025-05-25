package com.beiramar.beiramar.api.dto.agendamentosDtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO para listagem de Agendamentos sem expor dados sensíveis")
public class AgendamentoListagemDto {

    private Integer idAgendamento;
    private String nomeCliente;
    private String nomeFuncionario;
    private String nomeServico;
    private LocalDateTime dtHora;
    private Double valorPago;
    private String status;

    public AgendamentoListagemDto(Integer idAgendamento, String nomeCliente, String nomeFuncionario, String nomeServico, LocalDateTime dtHora, Double valorPago, String status) {
        this.idAgendamento = idAgendamento;
        this.nomeCliente = nomeCliente;
        this.nomeFuncionario = nomeFuncionario;
        this.nomeServico = nomeServico;
        this.dtHora = dtHora;
        this.valorPago = valorPago;
        this.status = status;
    }

    public Integer getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Integer idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
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
}
