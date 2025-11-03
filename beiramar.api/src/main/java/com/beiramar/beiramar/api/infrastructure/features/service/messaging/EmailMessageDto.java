package com.beiramar.beiramar.api.infrastructure.features.service.messaging;

public class EmailMessageDto {

    private String email;
    private String codigo;
    private Integer logId;

    public EmailMessageDto(String email, String codigo, Integer logId) {
        this.email = email;
        this.codigo = codigo;
        this.logId = logId;
    }

    public EmailMessageDto() {

    }

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

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }
}
