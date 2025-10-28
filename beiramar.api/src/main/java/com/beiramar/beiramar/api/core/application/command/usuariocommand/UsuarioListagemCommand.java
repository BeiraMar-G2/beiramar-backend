package com.beiramar.beiramar.api.core.application.command.usuariocommand;

import java.time.LocalDate;

public class UsuarioListagemCommand {

    private Integer idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String cargo;
    private String fotoUrl;

    public UsuarioListagemCommand(Integer idUsuario, String nome, String email, String telefone, String cargo, String fotoUrl) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
        this.fotoUrl = fotoUrl;
    }

    public Integer getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getCargo() { return cargo; }
    public String getFotoUrl() { return fotoUrl;}
}
