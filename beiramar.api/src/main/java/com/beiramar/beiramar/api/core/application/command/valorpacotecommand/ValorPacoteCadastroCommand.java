package com.beiramar.beiramar.api.core.application.command.valorpacotecommand;

import jakarta.validation.constraints.NotNull;

public class ValorPacoteCadastroCommand {

    @NotNull
    private Double valorTotal;

    @NotNull
    private Integer fkUsuario;

    @NotNull
    private Integer fkPacote;

    public ValorPacoteCadastroCommand(Double valorTotal, Integer fkUsuario, Integer fkPacote) {
        this.valorTotal = valorTotal;
        this.fkUsuario = fkUsuario;
        this.fkPacote = fkPacote;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public Integer getFkPacote() {
        return fkPacote;
    }
}
