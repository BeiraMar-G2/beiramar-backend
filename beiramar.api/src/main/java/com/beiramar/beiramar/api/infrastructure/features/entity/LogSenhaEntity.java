package com.beiramar.beiramar.api.infrastructure.features.entity;

import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Schema(description = "Log de recuperação de senha")
public class LogSenhaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLogSenha;
    private String token;
    private LocalDateTime dataHoraLogSenha;
    @Enumerated(EnumType.STRING)
    private StatusLogSenha status;

    @ManyToOne
    @JoinColumn(name = "fkUsuario")
    private UsuarioEntity usuario;

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

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
