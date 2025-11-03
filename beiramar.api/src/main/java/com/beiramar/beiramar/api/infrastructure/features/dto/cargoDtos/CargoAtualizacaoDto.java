package com.beiramar.beiramar.api.infrastructure.features.dto.cargoDtos;

import jakarta.validation.constraints.NotBlank;

public class CargoAtualizacaoDto {

    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
