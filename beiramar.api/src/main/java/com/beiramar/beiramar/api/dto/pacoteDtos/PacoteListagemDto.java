package com.beiramar.beiramar.api.dto.pacoteDtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para listagem de pacotes sem expor dados sensíveis")
public class PacoteListagemDto {

    @Schema(description = "Identificador único do pacote no DTO de listagem", example = "1")
    private Integer idPacote;

    @Schema(description = "Nome do pacote no DTO de listagem", example = "Pacote Premium")
    private String nome;

    @Schema(description = "Preço do pacote no DTO de listagem", example = "250.00")
    private Double precoTotalSemDesconto;

    @Schema(description = "Quantidade de sessões no DTO de listagem", example = "")
    private Integer qtdSessoesTotal;

    @Schema(description = "Tempo limite do pacote no DTO de listagem", example = "")
    private Integer tempoLimiteDias;

    public PacoteListagemDto(Integer idPacote, String nome, Double precoTotalSemDesconto, Integer qtdSessoesTotal, Integer tempoLimiteDias) {
        this.idPacote = idPacote;
        this.nome = nome;
        this.precoTotalSemDesconto = precoTotalSemDesconto;
        this.qtdSessoesTotal = qtdSessoesTotal;
        this.tempoLimiteDias = tempoLimiteDias;
    }

    public Integer getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(Integer idPacote) {
        this.idPacote = idPacote;
    }

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
