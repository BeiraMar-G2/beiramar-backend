package com.beiramar.beiramar.api.dto.valorPacoteDtos;

import jakarta.validation.constraints.NotNull;

public class ValorPacoteCadastroDto {

    @NotNull
    private Double valorTotal;

    @NotNull
    private Integer fkUsuario;

    @NotNull
    private Integer fkPacote;

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Integer getFkPacote() {
        return fkPacote;
    }

    public void setFkPacote(Integer fkPacote) {
        this.fkPacote = fkPacote;
    }
}
