package com.beiramar.beiramar.api.infrastructure.features.dto.pacoteDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PacoteAtualizacaoDto {

    @NotBlank
    private String nome;

    @NotNull
    private Double precoTotalSemDesconto;

    @NotNull
    private Integer qtdSessoesTotal;

    @NotNull
    private Integer tempoLimiteDias;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoTotalSemDesconto() {
        return precoTotalSemDesconto;
    }

    public void setPrecoTotalSemDesconto(Double precoTotalSemDesconto) {
        this.precoTotalSemDesconto = precoTotalSemDesconto;
    }

    public Integer getQtdSessoesTotal() {
        return qtdSessoesTotal;
    }

    public void setQtdSessoesTotal(Integer qtdSessoesTotal) {
        this.qtdSessoesTotal = qtdSessoesTotal;
    }

    public Integer getTempoLimiteDias() {
        return tempoLimiteDias;
    }

    public void setTempoLimiteDias(Integer tempoLimiteDias) {
        this.tempoLimiteDias = tempoLimiteDias;
    }
}
