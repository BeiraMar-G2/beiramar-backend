package com.beiramar.beiramar.api.core.application.command.usuariocommand;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class UsuarioAtualizacaoCommand {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String telefone;

    private LocalDate dtNasc;

    public UsuarioAtualizacaoCommand(String nome, String email, String telefone, LocalDate dtNasc) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dtNasc = dtNasc;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public LocalDate getDtNasc() { return dtNasc; }
}
