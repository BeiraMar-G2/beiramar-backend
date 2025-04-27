package com.beiramar.beiramar.api.dto;

public class ServicoCadastroDto {
    private String nome;
    private Double preco;
    private Integer duracao;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }
}
