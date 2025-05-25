package com.beiramar.beiramar.api.dto.autenticacaoDtos;

public class UsuarioTokenDto {

    private String nome;
    private String email;
    private String token;
    private String cargo;

    public UsuarioTokenDto(String nome, String email, String token, String cargo) {
        this.nome = nome;
        this.email = email;
        this.token = token;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
