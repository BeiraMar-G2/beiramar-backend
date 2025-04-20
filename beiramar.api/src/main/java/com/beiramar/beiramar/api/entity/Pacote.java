package com.beiramar.beiramar.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPacote;

    private Integer qtdSessaoTotal;
    private Integer qtdSessaoFalta;
    private Integer tempoLimiteDias;

    @OneToMany(mappedBy = "pacote")
    private List<SessoesPacote> sessoes;

    @OneToMany(mappedBy = "pacote")
    private List<ValorPacote> valores;


    public Integer getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(Integer idPacote) {
        this.idPacote = idPacote;
    }

    public Integer getQtdSessaoTotal() {
        return qtdSessaoTotal;
    }

    public void setQtdSessaoTotal(Integer qtdSessaoTotal) {
        this.qtdSessaoTotal = qtdSessaoTotal;
    }

    public Integer getQtdSessaoFalta() {
        return qtdSessaoFalta;
    }

    public void setQtdSessaoFalta(Integer qtdSessaoFalta) {
        this.qtdSessaoFalta = qtdSessaoFalta;
    }

    public Integer getTempoLimiteDias() {
        return tempoLimiteDias;
    }

    public void setTempoLimiteDias(Integer tempoLimiteDias) {
        this.tempoLimiteDias = tempoLimiteDias;
    }

    public List<SessoesPacote> getSessoes() {
        return sessoes;
    }

    public void setSessoes(List<SessoesPacote> sessoes) {
        this.sessoes = sessoes;
    }

    public List<ValorPacote> getValores() {
        return valores;
    }

    public void setValores(List<ValorPacote> valores) {
        this.valores = valores;
    }
}
