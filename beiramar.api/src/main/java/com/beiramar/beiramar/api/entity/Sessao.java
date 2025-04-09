package com.beiramar.beiramar.api.entity;

import jakarta.persistence.*;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer qtdSessoes;
    private Integer qtdSessoesConcluidas;

    @ManyToOne
    @JoinColumn(name = "id_pacote")
    private Pacote pacote;

    @ManyToOne
    @JoinColumn(name = "id_servico")
    private Servico servico;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQtdSessoes() {
        return qtdSessoes;
    }

    public void setQtdSessoes(Integer qtdSessoes) {
        this.qtdSessoes = qtdSessoes;
    }

    public Integer getQtdSessoesConcluidas() {
        return qtdSessoesConcluidas;
    }

    public void setQtdSessoesConcluidas(Integer qtdSessoesConcluidas) {
        this.qtdSessoesConcluidas = qtdSessoesConcluidas;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
