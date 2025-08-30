package com.beiramar.beiramar.api.core.application.command.usuariocommand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UsuarioCadastroCommand {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String telefone;
    @NotBlank
    private String senha;
    @NotNull
    private LocalDate dtNasc;
    @NotNull
    private Integer fkCargo;

    public UsuarioCadastroCommand(String nome, String email, String telefone, String senha, LocalDate dtNasc, Integer fkCargo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.dtNasc = dtNasc;
        this.fkCargo = fkCargo;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getSenha() { return senha; }
    public LocalDate getDtNasc() { return dtNasc; }
    public Integer getFkCargo() {return fkCargo; }
}
