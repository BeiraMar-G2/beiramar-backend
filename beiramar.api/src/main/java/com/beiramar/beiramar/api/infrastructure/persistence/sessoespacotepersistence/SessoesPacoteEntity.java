package com.beiramar.beiramar.api.infrastructure.persistence.sessoespacotepersistence;

import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "SessoesPacote")
@Schema(description = "Valor do pacote")
public class SessoesPacoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSessoesPacote;

    private Integer qtdSessoes;

    @ManyToOne
    @JoinColumn(name = "fkPacote")
    private PacoteEntity pacote;

    @ManyToOne
    @JoinColumn(name = "fkServico")
    private ServicoEntity servico;

    public Integer getIdSessoesPacote() {
        return idSessoesPacote;
    }

    public void setIdSessoesPacote(Integer idSessoesPacote) {
        this.idSessoesPacote = idSessoesPacote;
    }

    public Integer getQtdSessoes() {
        return qtdSessoes;
    }

    public void setQtdSessoes(Integer qtdSessoes) {
        this.qtdSessoes = qtdSessoes;
    }

    public PacoteEntity getPacote() {
        return pacote;
    }

    public void setPacote(PacoteEntity pacote) {
        this.pacote = pacote;
    }

    public ServicoEntity getServico() {
        return servico;
    }

    public void setServico(ServicoEntity servico) {
        this.servico = servico;
    }
}
