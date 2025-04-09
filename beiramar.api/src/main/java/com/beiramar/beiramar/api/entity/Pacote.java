package com.beiramar.beiramar.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPacote;

    private Double valorPacote;

    @OneToMany(mappedBy = "pacote")
    private List<PacoteDisponivel> pacotesDisponiveis;

    @OneToMany(mappedBy = "pacote")
    private List<Sessao> sessoes;

    @OneToMany(mappedBy = "pacote")
    private List<Agendamento> agendamentos;

    public Integer getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(Integer idPacote) {
        this.idPacote = idPacote;
    }

    public Double getValorPacote() {
        return valorPacote;
    }

    public void setValorPacote(Double valorPacote) {
        this.valorPacote = valorPacote;
    }

    public List<PacoteDisponivel> getPacotesDisponiveis() {
        return pacotesDisponiveis;
    }

    public void setPacotesDisponiveis(List<PacoteDisponivel> pacotesDisponiveis) {
        this.pacotesDisponiveis = pacotesDisponiveis;
    }

    public List<Sessao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(List<Sessao> sessoes) {
        this.sessoes = sessoes;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}
