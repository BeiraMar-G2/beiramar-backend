package com.beiramar.beiramar.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para listagem de Funcionários sem expor dados sensíveis")
public class FuncionarioListagemDto {

    @Schema(description = "Identificador único do funcionário no DTO de listagem", example = "1")
    private Integer id;
    @Schema(description = "Nome do funcionário no DTO de listagem", example = "Ana")
    private String nome;
    @Schema(description = "Email do funcionário no DTO de listagem", example = "ana@gmail.com")
    private String email;
    @Schema(description = "Telefone do funcionário no DTO de listagem", example = "11888888888")
    private String telefone;

    public FuncionarioListagemDto(Integer id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
