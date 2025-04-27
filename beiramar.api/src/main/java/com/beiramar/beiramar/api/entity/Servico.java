package com.beiramar.beiramar.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServico;

    private String nome;
    private Double preco;
    private Integer duracao;



    @OneToMany(mappedBy = "servico")
    private List<Agendamento> agendamentos;

    @OneToMany(mappedBy = "servico")
    private List<SessoesPacote> sessoes;



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

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<SessoesPacote> getSessoes() {
        return sessoes;
    }

    public void setSessoes(List<SessoesPacote> sessoes) {
        this.sessoes = sessoes;
    }
}
