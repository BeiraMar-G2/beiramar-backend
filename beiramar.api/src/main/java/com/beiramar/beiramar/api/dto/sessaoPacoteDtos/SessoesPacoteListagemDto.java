package com.beiramar.beiramar.api.dto.sessaoPacoteDtos;

public class SessoesPacoteListagemDto {


    private Integer idSessaoPacote;
    private String nomePacote;
    private String nomeServico;
    private Integer qtdSessoes;

    public SessoesPacoteListagemDto(Integer idSessaoPacote, String nomePacote, String nomeServico, Integer qtdSessoes) {
        this.idSessaoPacote = idSessaoPacote;
        this.nomePacote = nomePacote;
        this.nomeServico = nomeServico;
        this.qtdSessoes = qtdSessoes;
    }

    public Integer getIdSessaoPacote() {
        return idSessaoPacote;
    }

    public void setIdSessaoPacote(Integer idSessaoPacote) {
        this.idSessaoPacote = idSessaoPacote;
    }

    public String getNomePacote() {
        return nomePacote;
    }

    public void setNomePacote(String nomePacote) {
        this.nomePacote = nomePacote;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Integer getQtdSessoes() {
        return qtdSessoes;
    }

    public void setQtdSessoes(Integer qtdSessoes) {
        this.qtdSessoes = qtdSessoes;
    }
}
