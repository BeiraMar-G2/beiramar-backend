package com.beiramar.beiramar.api.service;


import com.beiramar.beiramar.api.repository.PacoteRepository;
import org.springframework.stereotype.Service;

@Service
public class PacoteService {

    private final PacoteRepository pacoteRepository;

    public PacoteService(PacoteRepository pacoteRepository) {
        this.pacoteRepository = pacoteRepository;
    }


}
