package com.beiramar.beiramar.api.core.domain;

import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoEntity;

import java.time.LocalDate;

public class Usuario {

    private Integer idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private LocalDate dtNasc;
    private Cargo cargo;
    private Integer fotoPerfilId;

    public Usuario(Integer idUsuario, String nome, String email, String telefone, String senha, LocalDate dtNasc, Cargo cargo, Integer fotoPerfilId) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.dtNasc = dtNasc;
        this.cargo = cargo;
        this.fotoPerfilId = fotoPerfilId;
    }

    public Usuario(String nome, String email, String telefone, String senha, LocalDate dtNasc, Cargo cargo) {
        this(null, nome, email, telefone, senha, dtNasc, cargo, null);
    }

    public Integer getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getSenha() { return senha; }
    public LocalDate getDtNasc() { return dtNasc; }
    public Cargo getCargo() { return cargo; }
    public Integer getFotoPerfilId() { return fotoPerfilId;}

    public void atualizar(String nome, String email, String telefone, LocalDate dtNasc) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dtNasc = dtNasc;
    }

    public void atualizarFotoPerfil(Integer fotoPerfilId) {
        this.fotoPerfilId = fotoPerfilId;
    }

    public void atualizarSenha(String senha) {
        this.senha = senha;
    }

}
