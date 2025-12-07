package com.beiramar.beiramar.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration; // Importar esta classe
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    // 1. Injetar as variáveis de ambiente/properties
    @Value("${spring.data.redis.host:localhost}") // Valor padrão é 'localhost'
    private String redisHost;

    @Value("${spring.data.redis.port:6379}") // Valor padrão é 6379
    private int redisPort;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // 2. Criar a configuração de conexão usando os valores injetados
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
        
        // Se você tivesse senha, adicionaria aqui: config.setPassword(redisPassword);
        
        // 3. Criar e retornar a LettuceConnectionFactory com a configuração
        return new LettuceConnectionFactory(config); 
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }
}