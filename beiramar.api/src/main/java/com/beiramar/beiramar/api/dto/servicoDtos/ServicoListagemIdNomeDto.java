package com.beiramar.beiramar.api.dto.servicoDtos;

public class ServicoListagemIdNomeDto {

    private Integer id;
    private String nome;

    public ServicoListagemIdNomeDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
}

