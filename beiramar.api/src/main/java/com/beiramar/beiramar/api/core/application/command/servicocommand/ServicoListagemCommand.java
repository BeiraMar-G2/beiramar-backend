package com.beiramar.beiramar.api.core.application.command.servicocommand;

public class ServicoListagemCommand {

    private Integer idServico;
    private String nome;
    private Double preco;
    private String descricao;
    private Integer duracao;

    public ServicoListagemCommand(Integer idServico, String nome, Double preco, String descricao, Integer duracao) {
        this.idServico = idServico;
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
}
