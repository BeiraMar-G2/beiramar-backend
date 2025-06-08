package com.beiramar.beiramar.api.config;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("s3")
public class AmazonConfig {

    @Value("${app.s3.access-key:}")
    private String accessKey;

    @Value("${app.s3.secret-key:}")
    private String secretKey;

    @Value("${app.s3.token:}")
    private String sessionToken;

    @Value("${app.s3.region:us-east-1}")
    private String region;

    @Bean
    public AmazonS3 amazonS3Client() {
        log.info("Initializing S3 client with region: {}", region);
        log.info("Access key configured: {}", !accessKey.isEmpty() && !accessKey.equals("NOT_FOUND"));
        log.info("Secret key configured: {}", !secretKey.isEmpty() && !secretKey.equals("NOT_FOUND"));
        log.info("Session token configured: {}", !sessionToken.isEmpty() && !sessionToken.equals("NOT_FOUND"));

        // Se as credenciais temporárias estão definidas (AWS Academy)
        if (!accessKey.isEmpty() && !secretKey.isEmpty() && !sessionToken.isEmpty() &&
                !accessKey.equals("NOT_FOUND") && !secretKey.equals("NOT_FOUND") && !sessionToken.equals("NOT_FOUND")) {

            log.info("Using temporary AWS credentials (with session token)");
            AWSCredentials credentials = new BasicSessionCredentials(accessKey, secretKey, sessionToken);
            return AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.fromName(region))
                    .build();
        }
        else if (!accessKey.isEmpty() && !secretKey.isEmpty() &&
                !accessKey.equals("NOT_FOUND") && !secretKey.equals("NOT_FOUND")) {

            log.info("Using permanent AWS credentials");
            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
            return AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.fromName(region))
                    .build();
        }

        log.info("Using default AWS credentials provider chain");
        return AmazonS3ClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(Regions.fromName(region))
                .build();
    }
}