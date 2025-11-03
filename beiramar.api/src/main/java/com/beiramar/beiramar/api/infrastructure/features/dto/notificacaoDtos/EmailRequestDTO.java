package com.beiramar.beiramar.api.infrastructure.features.dto.notificacaoDtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para envio do email de recuperação de senha sem expor dados sensíveis")
public class EmailRequestDTO {
    @Schema(description = "Email do usuário no DTO de recuperação de senha", example = "ivan.rangel@gmail.com")
    private String Email;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
