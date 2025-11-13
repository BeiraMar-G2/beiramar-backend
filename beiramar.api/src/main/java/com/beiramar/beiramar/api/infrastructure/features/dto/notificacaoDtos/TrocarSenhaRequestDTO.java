package com.beiramar.beiramar.api.infrastructure.features.dto.notificacaoDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class TrocarSenhaRequestDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String novaSenha;

    @NotBlank
    private String confirmarNovaSenha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmarNovaSenha() {
        return confirmarNovaSenha;
    }

    public void setConfirmarNovaSenha(String confirmarNovaSenha) {
        this.confirmarNovaSenha = confirmarNovaSenha;
    }
}
