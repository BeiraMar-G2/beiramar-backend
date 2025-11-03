package com.beiramar.beiramar.api.infrastructure.features.dto.agendamentosDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(description = "DTO para cadastro de agendamentos sem expor dados sensíveis")
public class AgendamentoCadastroDto {

    @NotNull
    private Integer fkServico;

    @NotNull
    private Integer fkCliente;

    @NotNull
    private Integer fkFuncionario;

    private Integer fkPacote;

    @NotNull
    private LocalDateTime dtHora;

    @NotNull
    private Double valorPago;

    @NotBlank
    private String status;

    @NotBlank
    private String statusAgendamento;

    public String getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(String statusAgendamento) {
        this.statusAgendamento = statusAgendamento;
    }

    public Integer getFkServico() {
        return fkServico;
    }

    public void setFkServico(Integer fkServico) {
        this.fkServico = fkServico;
    }

    public Integer getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Integer fkCliente) {
        this.fkCliente = fkCliente;
    }

    public Integer getFkFuncionario() {
        return fkFuncionario;
    }

    public void setFkFuncionario(Integer fkFuncionario) {
        this.fkFuncionario = fkFuncionario;
    }

    public Integer getFkPacote() {
        return fkPacote;
    }

    public void setFkPacote(Integer fkPacote) {
        this.fkPacote = fkPacote;
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
