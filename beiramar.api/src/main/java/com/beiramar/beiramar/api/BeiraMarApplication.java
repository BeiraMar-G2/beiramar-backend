package com.beiramar.beiramar.api;

import com.beiramar.beiramar.api.config.SendGridConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(SendGridConfigurationProperties.class)
public class BeiraMarApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeiraMarApplication.class, args);
	}

}
