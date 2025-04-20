package com.beiramar.beiramar.api.entity;

import jakarta.persistence.*;

@Entity
public class SessoesPacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer qtdSessoes;

    @ManyToOne
    @JoinColumn(name = "fkPacote")
    private Pacote pacote;

    @ManyToOne
    @JoinColumn(name = "fkServico")
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
