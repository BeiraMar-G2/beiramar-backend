package com.beiramar.beiramar.api.core.domain;

public class ValorPacote {

    private Integer idValorPacote;
    private Double valorTotal;
    private Usuario usuario;
    private Pacote pacote;

    public ValorPacote(Integer idValorPacote, Double valorTotal, Usuario usuario, Pacote pacote) {
        this.idValorPacote = idValorPacote;
        this.valorTotal = valorTotal;
        this.usuario = usuario;
        this.pacote = pacote;
    }

    public ValorPacote(Double valorTotal, Usuario usuario, Pacote pacote) {
        this.valorTotal = valorTotal;
        this.usuario = usuario;
        this.pacote = pacote;
    }

    public Integer getIdValorPacote() {
        return idValorPacote;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void atualizar(Double valorTotal){
        this.valorTotal = valorTotal;
    }
}
