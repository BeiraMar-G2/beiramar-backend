package com.beiramar.beiramar.api.core.domain;

import java.time.LocalDate;

public class Usuario {

    private Integer idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private Cargo cargo;
    private Integer fotoPerfilId;

    public Usuario(Integer idUsuario, String nome, String email, String telefone, String senha, Cargo cargo, Integer fotoPerfilId) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cargo = cargo;
        this.fotoPerfilId = fotoPerfilId;
    }

    public Usuario(String nome, String email, String telefone, String senha, Cargo cargo) {
        this(null, nome, email, telefone, senha, cargo, null);
    }

    public Integer getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getSenha() { return senha; }
    public Cargo getCargo() { return cargo; }
    public Integer getFotoPerfilId() { return fotoPerfilId;}

    public void atualizar(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public void atualizarFotoPerfil(Integer fotoPerfilId) {
        this.fotoPerfilId = fotoPerfilId;
    }

    public void atualizarSenha(String senha) {
        this.senha = senha;
    }

}
