package com.beiramar.beiramar.api.dto.servicoDtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para cadastro de serviços sem expor dados sensíveis")
public class ServicoCadastroDto {
    @Schema(description = "Nome do serviço no DTO de cadastro", example = "Design de Sobrancelha")
    private String nome;
    @Schema(description = "Preço do serviço no DTO de cadastro", example = "100,00")
    private Double preco;
    @Schema(description = "Descricao do serviço no DTO de cadastro", example = "")
    private String descricao;
    @Schema(description = "Duração em minutos do serviço no DTO de cadastro", example = "30")
    private Integer duracao;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }
}
