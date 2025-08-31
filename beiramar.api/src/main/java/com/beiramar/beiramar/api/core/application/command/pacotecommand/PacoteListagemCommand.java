package com.beiramar.beiramar.api.core.application.command.pacotecommand;

public class PacoteListagemCommand {

    private Integer idPacote;
    private String nome;
    private Double precoTotalSemDesconto;
    private Integer qtdSessoesTotal;
    private Integer tempoLimiteDias;

    public PacoteListagemCommand(Integer idPacote, String nome, Double precoTotalSemDesconto, Integer qtdSessoesTotal, Integer tempoLimiteDias) {
        this.idPacote = idPacote;
        this.nome = nome;
        this.precoTotalSemDesconto = precoTotalSemDesconto;
        this.qtdSessoesTotal = qtdSessoesTotal;
        this.tempoLimiteDias = tempoLimiteDias;
    }

    public Integer getIdPacote() {return idPacote;}
    public String getNome() {return nome;}
    public Double getPrecoTotalSemDesconto() {return precoTotalSemDesconto;}
    public Integer getQtdSessoesTotal() {return qtdSessoesTotal;}
    public Integer getTempoLimiteDias() {return tempoLimiteDias;}
}
