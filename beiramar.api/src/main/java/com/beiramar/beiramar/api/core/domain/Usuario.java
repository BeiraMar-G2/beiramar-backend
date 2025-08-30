package com.beiramar.beiramar.api.core.domain;

import java.time.LocalDate;

public class Usuario {

    private Integer idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private LocalDate dtNasc;
    private Cargo cargo;

    public Usuario(Integer idUsuario, String nome, String email, String telefone, String senha, LocalDate dtNasc, Cargo cargo) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.dtNasc = dtNasc;
        this.cargo = cargo;
    }

    public Usuario(String nome, String email, String telefone, String senha, LocalDate dtNasc, Cargo cargo) {
        this(null, nome, email, telefone, senha, dtNasc, cargo);
    }

    public Integer getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getSenha() { return senha; }
    public LocalDate getDtNasc() { return dtNasc; }
    public Cargo getCargo() { return cargo; }

    public void atualizar(String nome, String email, String telefone, LocalDate dtNasc) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dtNasc = dtNasc;
    }

    public void atualizarSenha(String senha) {
        this.senha = senha;
    }

}
