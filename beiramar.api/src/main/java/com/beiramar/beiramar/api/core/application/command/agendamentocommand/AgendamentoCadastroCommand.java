package com.beiramar.beiramar.api.core.application.command.agendamentocommand;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AgendamentoCadastroCommand {

    @NotNull
    private LocalDateTime dtHora;

    @NotNull
    private Double valorPago;

    @NotNull
    private String status;

    @NotNull
    private String statusAgendamento;

    @NotNull
    private Integer fkCliente;

    @NotNull
    private Integer fkFuncionario;

    @NotNull
    private Integer fkServico;

    private Integer fkPacote;

    public AgendamentoCadastroCommand(LocalDateTime dtHora, Double valorPago, String status,
                                      String statusAgendamento, Integer fkCliente, Integer fkFuncionario,
                                      Integer fkServico, Integer fkPacote) {
        this.dtHora = dtHora;
        this.valorPago = valorPago;
        this.status = status;
        this.statusAgendamento = statusAgendamento;
        this.fkCliente = fkCliente;
        this.fkFuncionario = fkFuncionario;
        this.fkServico = fkServico;
        this.fkPacote = fkPacote;
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

    public Integer getFkCliente() {
        return fkCliente;
    }

    public Integer getFkFuncionario() {
        return fkFuncionario;
    }

    public Integer getFkServico() {
        return fkServico;
    }

    public Integer getFkPacote() {
        return fkPacote;
    }

}
