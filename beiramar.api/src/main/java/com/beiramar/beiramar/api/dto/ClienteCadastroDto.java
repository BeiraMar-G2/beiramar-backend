package com.beiramar.beiramar.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class ClienteCadastroDto {

    @NotBlank
    private String nome;

    @NotBlank
    //@CPF
    private String cpf;

    @NotBlank
    //@Email
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    //@Size(min = 8, max = 15, message = "Senha deve ser de 8 a 15 digitos")
    private String senha;

    @NotNull
    private LocalDate dtNasc;


    public ClienteCadastroDto() {
    }

    public ClienteCadastroDto(String nome, String cpf, String email, String telefone, String senha, LocalDate dtNasc) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.dtNasc = dtNasc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }
}
