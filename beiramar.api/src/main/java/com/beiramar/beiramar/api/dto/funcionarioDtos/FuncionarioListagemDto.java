package com.beiramar.beiramar.api.dto.funcionarioDtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para listagem de Funcionários sem expor dados sensíveis")
public class FuncionarioListagemDto {

    @Schema(description = "Identificador único do funcionário no DTO de listagem", example = "1")
    private Integer idFuncionario;
    @Schema(description = "Nome do funcionário no DTO de listagem", example = "Ana")
    private String nome;
    @Schema(description = "Email do funcionário no DTO de listagem", example = "ana@gmail.com")
    private String email;
    @Schema(description = "Telefone do funcionário no DTO de listagem", example = "11888888888")
    private String telefone;
    @Schema(description = "Cargo do usuário no DTO de listagem", example = "funcionario")
    private String nomeCargo;

    public FuncionarioListagemDto(Integer idFuncionario, String nome, String email, String telefone, String nomeCargo) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.nomeCargo = nomeCargo;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

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

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}
