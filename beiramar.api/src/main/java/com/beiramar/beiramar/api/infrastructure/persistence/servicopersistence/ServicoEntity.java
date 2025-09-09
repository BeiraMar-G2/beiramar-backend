package com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence;

import com.beiramar.beiramar.api.infrastructure.persistence.agendamentopersistence.AgendamentoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.sessoespacotepersistence.SessoesPacoteEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Servico")
@Schema(description = "Serviços cadastrados no sistema")
public class ServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServico;

    private String nome;
    private Double preco;
    private String descricao;
    private Integer duracao;



    @OneToMany(mappedBy = "servico")
    private List<AgendamentoEntity> agendamentos;

    @OneToMany(mappedBy = "servico")
    private List<SessoesPacoteEntity> sessoes;

    public ServicoEntity(Integer id, String nome, Integer duracao, Double preco, String descricao){
    }

    public ServicoEntity() {

    }


    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public List<AgendamentoEntity> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<AgendamentoEntity> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<SessoesPacoteEntity> getSessoes() {
        return sessoes;
    }

    public void setSessoes(List<SessoesPacoteEntity> sessoes) {
        this.sessoes = sessoes;
    }
}
