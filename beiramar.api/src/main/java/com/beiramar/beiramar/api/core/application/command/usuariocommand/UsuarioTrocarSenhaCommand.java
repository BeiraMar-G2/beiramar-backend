package com.beiramar.beiramar.api.core.application.command.usuariocommand;

import jakarta.validation.constraints.NotBlank;

public class UsuarioTrocarSenhaCommand {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;

    @NotBlank
    private String confirmarNovaSenha;

    public UsuarioTrocarSenhaCommand(String senhaAtual, String novaSenha, String confirmarNovaSenha) {
        this.senhaAtual = senhaAtual;
        this.novaSenha = novaSenha;
        this.confirmarNovaSenha = confirmarNovaSenha;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public String getConfirmarNovaSenha() {
        return confirmarNovaSenha;
    }
}
