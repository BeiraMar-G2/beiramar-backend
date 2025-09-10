package com.beiramar.beiramar.api;

import com.beiramar.beiramar.api.config.SendGridConfigurationProperties;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SendGridConfigurationProperties.class)
public class BeiraMarApplication {

	public static void main(String[] args) {

		// Carrega as variáveis definidas no arquivo .env
		Dotenv dotenv = Dotenv.load();
		// Itera sobre todas as variáveis do .env e seta cada uma delas como propriedade do sistema System.setProperty
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		SpringApplication.run(BeiraMarApplication.class, args);
	}

}
