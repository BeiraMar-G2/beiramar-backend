package com.beiramar.beiramar.api.service;

public interface StorageService {


    void save(String fileName, byte[] content);

    byte[] load(String fileName);


    void delete(String fileName);
}