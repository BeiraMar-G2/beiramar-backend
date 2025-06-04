package com.beiramar.beiramar.api.dto.clienteDtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para listagem de clientes sem expor dados sensíveis")
public class ClienteListagemDto {

    @Schema(description = "Identificador único do usuário no DTO de listagem", example = "1")
    private Integer idCliente;
    @Schema(description = "Nome do usuário no DTO de listagem", example = "Gisele")
    private String nome;
    @Schema(description = "Email do usuário no DTO de listagem", example = "gisele@gmail.com")
    private String email;
    @Schema(description = "Telefone do usuário no DTO de listagem", example = "11999999999")
    private String telefone;
    @Schema(description = "Cargo do usuário no DTO de listagem", example = "cliente")
    private String nomeCargo;

    public ClienteListagemDto(Integer idCliente, String nome, String email, String telefone, String nomeCargo) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.nomeCargo = nomeCargo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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
