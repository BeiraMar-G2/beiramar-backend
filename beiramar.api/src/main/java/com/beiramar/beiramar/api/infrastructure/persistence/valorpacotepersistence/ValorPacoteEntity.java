package com.beiramar.beiramar.api.infrastructure.persistence.valorpacotepersistence;

import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "ValorPacote")
@Schema(description = "Valor do pacote")
public class ValorPacoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idValorPacote;

    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "fkUsuario")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "fkPacote")
    private PacoteEntity pacote;


    public Integer getIdValorPacote() {
        return idValorPacote;
    }

    public void setIdValorPacote(Integer idValorPacote) {
        this.idValorPacote = idValorPacote;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public PacoteEntity getPacote() {
        return pacote;
    }

    public void setPacote(PacoteEntity pacote) {
        this.pacote = pacote;
    }
}
