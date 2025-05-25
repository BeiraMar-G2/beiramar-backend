package com.beiramar.beiramar.api.dto.pacoteDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para cadastro de pacotes sem expor dados sensíveis")
public class PacoteCadastroDto {

    @Schema(description = "Nome do pacote no DTO de cadastro", example = "Pacote Premium")
    @NotBlank
    private String nome;

    @Schema(description = "Preço do pacote no DTO de cadastro", example = "250.00")
    @NotNull
    private Double precoTotalSemDesconto;

    @Schema(description = "Quantidade de sessões no DTO de cadastro", example = "")
    @NotNull
    private Integer qtdSessoesTotal;

    @Schema(description = "Tempo limite no DTO de cadastro", example = "")
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
