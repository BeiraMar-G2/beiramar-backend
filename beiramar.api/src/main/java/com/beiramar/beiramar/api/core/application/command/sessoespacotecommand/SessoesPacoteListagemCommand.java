package com.beiramar.beiramar.api.core.application.command.sessoespacotecommand;

public class SessoesPacoteListagemCommand {

    private Integer idSessoesPacote;
    private Integer qtdSessoes;
    private String pacote;
    private String servico;

    public SessoesPacoteListagemCommand(Integer idSessoesPacote, Integer qtdSessoes, String pacote, String servico) {
        this.idSessoesPacote = idSessoesPacote;
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

    public String getPacote() {
        return pacote;
    }

    public String getServico() {
        return servico;
    }
}
