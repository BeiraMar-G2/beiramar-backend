package com.beiramar.beiramar.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
public class SessoesPacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSessoesPacote;

    private Integer qtdSessoes;

    @ManyToOne
    @JoinColumn(name = "fkPacote")
    private Pacote pacote;

    @ManyToOne
    @JoinColumn(name = "fkServico")
    private Servico servico;

    public Integer getIdSessoesPacote() {
        return idSessoesPacote;
    }

    public void setIdSessoesPacote(Integer idSessoesPacote) {
        this.idSessoesPacote = idSessoesPacote;
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
