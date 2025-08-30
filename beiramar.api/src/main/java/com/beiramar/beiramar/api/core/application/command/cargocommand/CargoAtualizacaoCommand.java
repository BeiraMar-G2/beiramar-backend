package com.beiramar.beiramar.api.core.application.command.cargocommand;

import jakarta.validation.constraints.NotBlank;

public class CargoAtualizacaoCommand {

    @NotBlank
    private String nome;

    public CargoAtualizacaoCommand(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
