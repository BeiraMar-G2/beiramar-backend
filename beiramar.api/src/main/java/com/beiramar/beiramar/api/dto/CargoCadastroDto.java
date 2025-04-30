package com.beiramar.beiramar.api.dto;

import jakarta.validation.constraints.NotBlank;

public class CargoCadastroDto {

    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
