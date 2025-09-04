package com.beiramar.beiramar.api.core.application.command.valorpacotecommand;

import jakarta.validation.constraints.NotNull;

public class ValorPacoteAtulizacaoCommand {

    @NotNull
    private Double valorTotal;

    public ValorPacoteAtulizacaoCommand(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorTotal() {
        return valorTotal;
    }
}
