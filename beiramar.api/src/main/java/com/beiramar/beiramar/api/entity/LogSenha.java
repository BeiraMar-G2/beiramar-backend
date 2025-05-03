package com.beiramar.beiramar.api.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class LogSenha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLogSenha;
    private String token;
    private LocalDateTime dataHoraLogSenha;
    @Enumerated(EnumType.STRING)
    private StatusLogSenha status;

    @ManyToOne
    @JoinColumn(name = "fkUsuario")
    private Usuario usuario;

    public Integer getIdLogSenha() {
        return idLogSenha;
    }

    public void setIdLogSenha(Integer idLogSenha) {
        this.idLogSenha = idLogSenha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getDataHoraLogSenha() {
        return dataHoraLogSenha;
    }

    public void setDataHoraLogSenha(LocalDateTime dataHoraLogSenha) {
        this.dataHoraLogSenha = dataHoraLogSenha;
    }

    public StatusLogSenha getStatus() {
        return status;
    }

    public void setStatus(StatusLogSenha status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
