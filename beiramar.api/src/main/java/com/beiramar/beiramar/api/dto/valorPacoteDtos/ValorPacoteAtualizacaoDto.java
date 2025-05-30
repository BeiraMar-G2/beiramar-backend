package com.beiramar.beiramar.api.dto.valorPacoteDtos;

import jakarta.validation.constraints.NotNull;

public class ValorPacoteAtualizacaoDto {

    @NotNull
    private Double valorTotal;

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
