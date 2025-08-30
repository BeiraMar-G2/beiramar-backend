package com.beiramar.beiramar.api.core.application.command.cargocommand;

import jakarta.validation.constraints.NotBlank;

public class CargoCadastroCommand {

    @NotBlank
    private String nome;

    public CargoCadastroCommand(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
