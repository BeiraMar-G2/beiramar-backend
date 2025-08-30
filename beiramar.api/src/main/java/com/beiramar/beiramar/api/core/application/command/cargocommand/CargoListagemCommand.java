package com.beiramar.beiramar.api.core.application.command.cargocommand;

public class CargoListagemCommand {

    private Integer idCargo;
    private String nome;

    public CargoListagemCommand(Integer idCargo, String nome) {
        this.idCargo = idCargo;
        this.nome = nome;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public String getNome() {
        return nome;
    }
}
