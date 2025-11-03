package com.beiramar.beiramar.api.infrastructure.features.dto.autenticacaoDtos;

public class UsuarioLoginDto {

    private String email;
    private String senha;

    public UsuarioLoginDto(String mail, String senha123) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
