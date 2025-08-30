package com.beiramar.beiramar.api.core.application.command.usuariocommand;

import java.time.LocalDate;

public class UsuarioListagemCommand {

    private Integer idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dtNasc;
    private String cargo;

    public UsuarioListagemCommand(Integer idUsuario, String nome, String email, String telefone, LocalDate dtNasc, String cargo) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dtNasc = dtNasc;
        this.cargo = cargo;
    }

    public Integer getIdUsuario() { return idUsuario; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public LocalDate getDtNasc() { return dtNasc; }
    public String getCargo() { return cargo; }
}
