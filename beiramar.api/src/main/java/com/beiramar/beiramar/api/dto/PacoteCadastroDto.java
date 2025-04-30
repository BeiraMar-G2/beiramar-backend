package com.beiramar.beiramar.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para cadastro de pacotes sem expor dados sensíveis")
public class PacoteCadastroDto {

    @Schema(description = "Nome do pacote no DTO de cadastro", example = "Pacote Premium")
    private String nome;

    @Schema(description = "Preço do pacote no DTO de cadastro", example = "250.00")
    private Double preco;

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
