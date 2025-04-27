package com.beiramar.beiramar.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Schema(description = "DTO para cadastro de Funcionários sem expor dados sensíveis")
public class FuncionarioCadastroDto {

    @Schema(description = "Nome do funcionário no DTO de cadastro", example = "Ana")    @NotBlank
    private String nome;

    @Schema(description = "Email do funcionário no DTO de cadastro", example = "ana@gmail.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Telefone do funcionário no DTO de cadastro", example = "11888888888")
    @NotBlank
    private String telefone;

    @Schema(description = "Senha do funcionário no DTO de cadastro", example = "Senha321")
    @NotBlank
    @Size(min = 8, max = 15, message = "Senha deve ser de 8 a 15 digitos")
    private String senha;

    @Schema(description = "Data de nascimento do funcionário no DTO de cadastro", example = "2001-01-01")
    @NotNull
    private LocalDate dtNasc;


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
