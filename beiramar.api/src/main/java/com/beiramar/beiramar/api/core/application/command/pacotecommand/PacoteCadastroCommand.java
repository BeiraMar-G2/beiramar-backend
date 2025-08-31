package com.beiramar.beiramar.api.core.application.command.pacotecommand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PacoteCadastroCommand {

    @NotBlank
    private String nome;
    @NotNull
    private Double precoTotalSemDesconto;
    @NotNull
    private Integer qtdSessoesTotal;
    @NotNull
    private Integer tempoLimiteDias;

    public PacoteCadastroCommand(String nome, Double precoTotalSemDesconto,
                                  Integer qtdSessoesTotal, Integer tempoLimiteDias) {
        this.nome = nome;
        this.precoTotalSemDesconto = precoTotalSemDesconto;
        this.qtdSessoesTotal = qtdSessoesTotal;
        this.tempoLimiteDias = tempoLimiteDias;
    }

    public String getNome() { return nome; }
    public Double getPrecoTotalSemDesconto() { return precoTotalSemDesconto; }
    public Integer getQtdSessoesTotal() { return qtdSessoesTotal; }
    public Integer getTempoLimiteDias() { return tempoLimiteDias; }
}
