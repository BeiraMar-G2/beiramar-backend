package com.beiramar.beiramar.api.dto.autenticacaoDtos;

public class UsuarioTokenDto {

    private Integer id;
    private String nome;
    private String email;
    private String token;
    private String cargo;

    public UsuarioTokenDto(Integer id, String nome, String email, String token, String cargo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.token = token;
        this.cargo = cargo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
