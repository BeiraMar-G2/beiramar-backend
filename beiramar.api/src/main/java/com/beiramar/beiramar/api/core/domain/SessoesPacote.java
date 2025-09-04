package com.beiramar.beiramar.api.core.domain;

public class SessoesPacote {

    private Integer idSessoesPacote;
    private Integer qtdSessoes;
    private Pacote pacote;
    private Servico servico;

    public SessoesPacote(Integer idSessoesPacote, Integer qtdSessoes, Pacote pacote, Servico servico) {
        this.idSessoesPacote = idSessoesPacote;
        this.qtdSessoes = qtdSessoes;
        this.pacote = pacote;
        this.servico = servico;
    }

    public SessoesPacote(Integer qtdSessoes, Pacote pacote, Servico servico) {
        this.qtdSessoes = qtdSessoes;
        this.pacote = pacote;
        this.servico = servico;
    }

    public Integer getIdSessoesPacote() {
        return idSessoesPacote;
    }

    public Integer getQtdSessoes() {
        return qtdSessoes;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public Servico getServico() {
        return servico;
    }

    public void atualizar(Integer qtdSessoes){
        this.qtdSessoes = qtdSessoes;
    }
}
