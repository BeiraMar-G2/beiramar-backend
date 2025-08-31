package com.beiramar.beiramar.api.core.domain;

public class Servico {

    private Integer idServico;
    private String nome;
    private Double preco;
    private String descricao;
    private Integer duracao;

    public Servico(Integer idServico, String nome, Double preco, String descricao, Integer duracao) {
        this.idServico = idServico;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.duracao = duracao;
    }

    public Servico(String nome, Double preco, String descricao, Integer duracao) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.duracao = duracao;
    }

    public Integer getIdServico() {
        return idServico;
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

    public void atualizar(String nome, Double preco, String descricao, Integer duracao) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.duracao = duracao;
    }
}
