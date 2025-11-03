package com.beiramar.beiramar.api.infrastructure.features.dto.clienteDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Schema(description = "DTO para cadastro de clientes sem expor dados sensíveis")
public class ClienteCadastroDto {

    @Schema(description = "Nome do usuário no DTO de cadastro", example = "Gisele")
    @NotBlank
    private String nome;

    @Schema(description = "Email do usuário no DTO de cadastro", example = "gisele@gmail.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Telefone do usuário no DTO de cadastro", example = "11999999999")
    @NotBlank
    private String telefone;

    @Schema(description = "Senha do usuário no DTO de cadastro", example = "Senha123")
    @NotBlank
    @Size(min = 8, max = 15, message = "Senha deve ser de 8 a 15 dígitos")
    private String senha;

    @Schema(description = "Cargo do Usuario", example = "Funcionario/Cliente")
    @NotNull
    private Integer fkCargo;


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

    public Integer getFkCargo() {
        return fkCargo;
    }

    public void setFkCargo(Integer fkCargo) {
        this.fkCargo = fkCargo;
    }
}
