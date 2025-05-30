package com.beiramar.beiramar.api.dto.valorPacoteDtos;

public class ValorPacoteListagemDto {

    private Integer idValorPacote;
    private Double valorTotal;
    private String nomeUsuario;
    private String nomePacote;

    public ValorPacoteListagemDto(Integer idValorPacote, Double valorTotal, String nomeUsuario, String nomePacote) {
        this.idValorPacote = idValorPacote;
        this.valorTotal = valorTotal;
        this.nomeUsuario = nomeUsuario;
        this.nomePacote = nomePacote;
    }

    public Integer getIdValorPacote() {
        return idValorPacote;
    }

    public void setIdValorPacote(Integer idValorPacote) {
        this.idValorPacote = idValorPacote;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomePacote() {
        return nomePacote;
    }

    public void setNomePacote(String nomePacote) {
        this.nomePacote = nomePacote;
    }
}
