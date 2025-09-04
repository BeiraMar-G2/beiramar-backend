package com.beiramar.beiramar.api.core.application.command.sessoespacotecommand;

import jakarta.validation.constraints.NotNull;

public class SessoesPacoteCadastroCommand {

    @NotNull
    private Integer qtdSessoes;

    @NotNull
    private Integer fkPacote;

    @NotNull
    private Integer fkServico;

    public SessoesPacoteCadastroCommand(Integer qtdSessoes, Integer fkPacote, Integer fkServico) {
        this.qtdSessoes = qtdSessoes;
        this.fkPacote = fkPacote;
        this.fkServico = fkServico;
    }

    public Integer getQtdSessoes() {
        return qtdSessoes;
    }

    public Integer getFkPacote() {
        return fkPacote;
    }

    public Integer getFkServico() {
        return fkServico;
    }
}
