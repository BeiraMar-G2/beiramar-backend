package com.beiramar.beiramar.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para listagem de pacotes sem expor dados sensíveis")
public class PacoteListagemDto {

    @Schema(description = "Identificador único do pacote no DTO de listagem", example = "1")
    private Integer idPacote;

    @Schema(description = "Nome do pacote no DTO de listagem", example = "Pacote Premium")
    private String nome;

    @Schema(description = "Preço do pacote no DTO de listagem", example = "250.00")
    private Double preco;

    @Schema(description = "Duração total do pacote em minutos no DTO de listagem", example = "90")
    private Integer duracao;

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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
