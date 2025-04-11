package com.beiramar.beiramar.api.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class FuncionarioCadastroDto {


    @NotBlank
    private String nome;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String cargo;

    @NotBlank
    private String telefone;

    @NotBlank
    @Size(min = 8, max = 8, message = "CEP deve ter 8 digitos")
    private String cep;

    @NotBlank
    private String logradouro;

    @NotNull
    private Integer numeroLogradouro;

    private String complementoEndereco;

    @NotBlank
    @Size(min = 8, max = 15, message = "Senha deve ser de 8 a 15 digitos")
    private String senha;

    @NotNull
    private LocalDate dtNasc;


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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumeroLogradouro() {
        return numeroLogradouro;
    }

    public void setNumeroLogradouro(Integer numeroLogradouro) {
        this.numeroLogradouro = numeroLogradouro;
    }

    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
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
