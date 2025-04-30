package com.beiramar.beiramar.api.dto;

public class CargoListagemDto {

    private Integer idCargo;
    private String nome;

    public CargoListagemDto(Integer idCargo, String nome) {
        this.idCargo = idCargo;
        this.nome = nome;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
