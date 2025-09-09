package com.beiramar.beiramar.api.core.application.command.agendamentocommand;

import java.time.LocalDateTime;

public class AgendamentoListagemCommand {

    private Integer idAgendamento;
    private LocalDateTime dtHora;
    private Double valorPago;
    private String status;
    private String cliente;
    private String funcionario;
    private String servico;
    private String pacote;

    public AgendamentoListagemCommand(Integer idAgendamento, LocalDateTime dtHora, Double valorPago,
                                      String status, String cliente, String funcionario,
                                      String servico, String pacote) {
        this.idAgendamento = idAgendamento;
        this.dtHora = dtHora;
        this.valorPago = valorPago;
        this.status = status;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.servico = servico;
        this.pacote = pacote;
    }

    public Integer getIdAgendamento() { return idAgendamento; }
    public LocalDateTime getDtHora() { return dtHora; }
    public Double getValorPago() { return valorPago; }
    public String getStatus() { return status; }
    public String getCliente() { return cliente; }
    public String getFuncionario() { return funcionario; }
    public String getServico() { return servico; }
    public String getPacote() { return pacote; }
}
