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
    private Double precoTotalSemDesconto;
    private Integer qtdSessoesTotal;
    private Integer tempoLimiteDias;

    @OneToMany(mappedBy = "pacote")
    private List<SessoesPacote> sessoes;

    @OneToMany(mappedBy = "pacote")
    private List<Agendamento> agendamentos;

    @OneToMany(mappedBy = "pacote")
    private List<ValorPacote> valoresPacote;


    public Pacote(Integer id, String nome, Double preco, Integer qtdSessoes, Integer diasLimite){

    }

    public Pacote() {

    }

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

    public Double getPrecoTotalSemDesconto() {
        return precoTotalSemDesconto;
    }

    public void setPrecoTotalSemDesconto(Double precoTotalSemDesconto) {
        this.precoTotalSemDesconto = precoTotalSemDesconto;
    }

    public Integer getQtdSessoesTotal() {
        return qtdSessoesTotal;
    }

    public void setQtdSessoesTotal(Integer qtdSessoesTotal) {
        this.qtdSessoesTotal = qtdSessoesTotal;
    }

    public Integer getTempoLimiteDias() {
        return tempoLimiteDias;
    }

    public void setTempoLimiteDias(Integer tempoLimiteDias) {
        this.tempoLimiteDias = tempoLimiteDias;
    }

    public List<SessoesPacote> getSessoes() {
        return sessoes;
    }

    public void setSessoes(List<SessoesPacote> sessoes) {
        this.sessoes = sessoes;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<ValorPacote> getValoresPacote() {
        return valoresPacote;
    }

    public void setValoresPacote(List<ValorPacote> valoresPacote) {
        this.valoresPacote = valoresPacote;
    }
}
