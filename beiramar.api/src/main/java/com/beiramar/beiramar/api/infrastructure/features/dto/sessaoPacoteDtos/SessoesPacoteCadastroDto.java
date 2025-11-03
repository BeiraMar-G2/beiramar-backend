package com.beiramar.beiramar.api.infrastructure.features.dto.sessaoPacoteDtos;

import jakarta.validation.constraints.NotNull;

public class SessoesPacoteCadastroDto {

    @NotNull
    private Integer fkPacote;

    @NotNull
    private Integer fkServico;

    @NotNull
    private Integer qtdSessoes;

    public Integer getFkPacote() {
        return fkPacote;
    }

    public void setFkPacote(Integer fkPacote) {
        this.fkPacote = fkPacote;
    }

    public Integer getFkServico() {
        return fkServico;
    }

    public void setFkServico(Integer fkServico) {
        this.fkServico = fkServico;
    }

    public Integer getQtdSessoes() {
        return qtdSessoes;
    }

    public void setQtdSessoes(Integer qtdSessoes) {
        this.qtdSessoes = qtdSessoes;
    }
}
