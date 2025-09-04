package com.beiramar.beiramar.api.core.application.command.sessoespacotecommand;

import jakarta.validation.constraints.NotNull;

public class SessoesPacoteAtualizacaoCommand {

    @NotNull
    private Integer qtdSessoes;

    public SessoesPacoteAtualizacaoCommand(Integer qtdSessoes) {
        this.qtdSessoes = qtdSessoes;
    }

    public Integer getQtdSessoes() {
        return qtdSessoes;
    }
}
