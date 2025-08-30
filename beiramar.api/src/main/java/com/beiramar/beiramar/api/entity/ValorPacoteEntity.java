package com.beiramar.beiramar.api.entity;

import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
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
    private Pacote pacote;


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

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }
}
