package com.beiramar.beiramar.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para recuperação de senha sem expor dados sensíveis")
public class CodigoSenhaRequestDTO {
    @Schema(description = "Email do usuário no DTO de código de recuperação de senha", example = "ivan.rangel@gmail.com")
    private String email;
    @Schema(description = "Email do usuário no DTO de código de recuperação de senha", example = "123456")
    private String codigo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
