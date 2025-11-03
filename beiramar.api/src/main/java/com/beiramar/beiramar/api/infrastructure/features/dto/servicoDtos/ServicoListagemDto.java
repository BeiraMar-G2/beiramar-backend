package com.beiramar.beiramar.api.infrastructure.features.dto.servicoDtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para listagem de serviços sem expor dados sensíveis")
public class ServicoListagemDto {
    @Schema(description = "Identificador único do serviço no DTO de listagem", example = "1")
    private Integer idServico;
    @Schema(description = "Nome do serviço no DTO de listagem", example = "Design de Sobrancelha")
    private String nome;
    @Schema(description = "Preço do serviço no DTO de listagem", example = "100,00")
    private Double preco;
    @Schema(description = "Descrição do serviço no DTO de listagem", example = "")
    private String descricao;
    @Schema(description = "Duração em minutos do serviço no DTO de listagem", example = "30")
    private Integer duracao;

    public ServicoListagemDto(Integer idServico, String nome, Double preco, String descricao, Integer duracao) {
        this.idServico = idServico;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.duracao = duracao;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
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
