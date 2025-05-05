package com.beiramar.beiramar.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Schema(description = "Pacote de serviços")
public class Pacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPacote;
    private String nome;
    private Double preco;
    private Integer qtdSessaoTotal;
    private Integer qtdSessaoFalta;
    private Integer tempoLimiteDias;


    public Integer getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(Integer idPacote) {
        this.idPacote = idPacote;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQtdSessaoFalta() {
        return qtdSessaoFalta;
    }

    public void setQtdSessaoFalta(Integer qtdSessaoFalta) {
        this.qtdSessaoFalta = qtdSessaoFalta;
    }

    public Integer getQtdSessaoTotal() {
        return qtdSessaoTotal;
    }

    public void setQtdSessaoTotal(Integer qtdSessaoTotal) {
        this.qtdSessaoTotal = qtdSessaoTotal;
    }

    public Integer getTempoLimiteDias() {
        return tempoLimiteDias;
    }

    public void setTempoLimiteDias(Integer tempoLimiteDias) {
        this.tempoLimiteDias = tempoLimiteDias;
    }
}
