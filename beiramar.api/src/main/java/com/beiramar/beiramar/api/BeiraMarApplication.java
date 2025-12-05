package com.beiramar.beiramar.api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class BeiraMarApplication {

	public static void main(String[] args) {

		// Carrega as variáveis definidas no arquivo .env
		Dotenv dotenv = Dotenv.configure().directory("/home/ec2-user/backend/api/").load();
		// Itera sobre todas as variáveis do .env e seta cada uma delas como propriedade do sistema System.setProperty
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		SpringApplication.run(BeiraMarApplication.class, args);
	}

}
