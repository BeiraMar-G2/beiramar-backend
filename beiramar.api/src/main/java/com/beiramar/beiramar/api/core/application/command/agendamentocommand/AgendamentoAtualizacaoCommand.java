package com.beiramar.beiramar.api.core.application.command.agendamentocommand;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AgendamentoAtualizacaoCommand {

    @NotNull
    private LocalDateTime dtHora;

    @NotNull
    private Double valorPago;

    @NotNull
    private String status;

    @NotNull
    private String statusAgendamento;

    public AgendamentoAtualizacaoCommand(LocalDateTime dtHora, Double valorPago, String status, String statusAgendamento) {
        this.dtHora = dtHora;
        this.valorPago = valorPago;
        this.status = status;
        this.statusAgendamento = statusAgendamento;
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
}
