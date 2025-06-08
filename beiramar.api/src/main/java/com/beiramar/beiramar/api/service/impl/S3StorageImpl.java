package com.beiramar.beiramar.api.service.impl;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.beiramar.beiramar.api.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.IOException;


@Slf4j
@Service
@Profile("s3")
public class S3StorageImpl implements StorageService {
    private final AmazonS3 s3Client;
    private final String bucketName;


    public S3StorageImpl(AmazonS3 s3Client, @Value("${app.s3.bucket}") String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }


    @Override
    public void save(String fileName, byte[] content) {
        try {
            log.info("[AWS] Saving file to S3 bucket: {} with filename: {}", bucketName, fileName);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(content.length);
            s3Client.putObject(bucketName, fileName, new ByteArrayInputStream(content), metadata);
        } catch (AmazonServiceException e) {
            log.error("[AWS-SERVICE-ERROR] Failed to save file to S3", e);
            throw new ResponseStatusException(500, "Service error: Unable to save file to S3.", e);
        } catch (AmazonClientException e) {
            log.error("[AWS-CLIENT-ERROR] Client error in connecting to S3", e);
            throw new ResponseStatusException(503, "Client error: Unable to connect to S3.", e);
        }
    }


    @Override
    public byte[] load(String fileName) {
        try {
            log.info("[AWS] Loading file {} from S3 bucket: {}", fileName, bucketName);
            S3Object s3Object = s3Client.getObject(bucketName, fileName);
            return s3Object.getObjectContent().readAllBytes();
        } catch (IOException e) {
            log.error("[AWS-IO-ERROR] Failed to load file from S3", e);
            throw new ResponseStatusException(500, "IO error: Unable to read file from S3.", e);
        } catch (AmazonServiceException e) {
            log.error("[AWS-SERVICE-ERROR] Service error on S3 operation", e);
            throw new ResponseStatusException(500, "Service error: Unable to load file from S3.", e);
        } catch (AmazonClientException e) {
            log.error("[AWS-CLIENT-ERROR] Client error in connecting to S3", e);
            throw new ResponseStatusException(503, "Client error: Unable to connect to S3.", e);
        }
    }


    @Override
    public void delete(String fileName) {
        try {
            log.info("[AWS] Deleting file {} from S3 bucket: {}", fileName, bucketName);
            s3Client.deleteObject(bucketName, fileName);
        } catch (AmazonServiceException e) {
            log.error("[AWS-SERVICE-ERROR] Failed to delete file from S3", e);
            throw new ResponseStatusException(500, "Service error: Unable to delete file from S3.", e);
        } catch (AmazonClientException e) {
            log.error("[AWS-CLIENT-ERROR] Client error in connecting to S3", e);
            throw new ResponseStatusException(503, "Client error: Unable to connect to S3.", e);
        }
    }
}
