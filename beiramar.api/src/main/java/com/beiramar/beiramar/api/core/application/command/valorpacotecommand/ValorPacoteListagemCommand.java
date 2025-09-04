package com.beiramar.beiramar.api.core.application.command.valorpacotecommand;

public class ValorPacoteListagemCommand {

    private Integer idValorPacote;
    private Double valorTotal;
    private String pacote;
    private String usuario;

    public ValorPacoteListagemCommand(Integer idValorPacote, Double valorTotal, String pacote, String usuario) {
        this.idValorPacote = idValorPacote;
        this.valorTotal = valorTotal;
        this.pacote = pacote;
        this.usuario = usuario;
    }

    public Integer getIdValorPacote() {
        return idValorPacote;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public String getPacote() {
        return pacote;
    }

    public String getUsuario() {
        return usuario;
    }
}
