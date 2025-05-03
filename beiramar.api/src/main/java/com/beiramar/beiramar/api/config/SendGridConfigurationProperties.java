package com.beiramar.beiramar.api.config;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "com.baeldung.sendgrid")

public class SendGridConfigurationProperties{
    @NotBlank
    @Pattern(regexp = "^SG\\.[0-9a-zA-Z\\-_]{22}\\.[0-9a-zA-Z\\-_]{43}$")
    private String apiKey;

    @Email
    @NotBlank
    private String fromEmail;

    @NotBlank
    private String fromName;

    public @NotBlank @Pattern(regexp = "^SG\\.[0-9a-zA-Z\\-_]{22}\\.[0-9a-zA-Z\\-_]{43}$") String getApiKey() {
        return apiKey;
    }

    public void setApiKey(@NotBlank @Pattern(regexp = "^SG\\.[0-9a-zA-Z\\-_]{22}\\.[0-9a-zA-Z\\-_]{43}$") String apiKey) {
        this.apiKey = apiKey;
    }

    public @Email @NotBlank String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(@Email @NotBlank String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public void setFromName(@NotBlank String fromName) {
        this.fromName = fromName;
    }

    public @NotBlank String getFromName() {
        return fromName;
    }
}
