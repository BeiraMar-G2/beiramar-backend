package com.beiramar.beiramar.api.core.application.command.servicocommand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ServicoAtualizacaoCommand {

    @NotBlank
    private String nome;

    @NotNull
    private Double preco;

    private String descricao;

    @NotNull
    private Integer duracao;

    public ServicoAtualizacaoCommand(String nome, Double preco, String descricao, Integer duracao) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.duracao = duracao;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getDuracao() {
        return duracao;
    }
}
