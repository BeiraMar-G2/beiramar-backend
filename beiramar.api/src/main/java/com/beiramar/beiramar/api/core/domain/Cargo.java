package com.beiramar.beiramar.api.core.domain;

public class Cargo {

    private Integer idCargo;
    private String nome;

    public Cargo(Integer idCargo, String nome) {
        this.idCargo = idCargo;
        this.nome = nome;
    }

    public Cargo(String nome) {
        this.nome = nome;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public String getNome() {
        return nome;
    }

    public void atualizarNome(String novoNome) {
        this.nome = novoNome;
    }
}
