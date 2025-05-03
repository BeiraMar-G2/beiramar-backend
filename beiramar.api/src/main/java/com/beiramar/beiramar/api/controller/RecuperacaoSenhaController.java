package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.CodigoSenhaRequestDTO;
import com.beiramar.beiramar.api.dto.EmailRequestDTO;
import com.beiramar.beiramar.api.repository.LogSenhaRepository;
import com.beiramar.beiramar.api.service.RecuperacaoSenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recuperacao")
public class RecuperacaoSenhaController {
    @Autowired
    private RecuperacaoSenhaService service;

    @Autowired
    private LogSenhaRepository logSenhaRepository;

    @PostMapping("/enviar-codigo")
    public ResponseEntity<String> enviarCodigo(@RequestBody EmailRequestDTO request){
        service.enviarCodigo(request.getEmail());
        return ResponseEntity.ok("Código enviado com sucesso");
    }

    @PostMapping("/validar-codigo")
    public ResponseEntity<String> validarCodigo(@RequestBody CodigoSenhaRequestDTO dto){
        boolean isValido = service.validarCodigo(dto.getEmail(), dto.getCodigo());
        if(isValido){
            return ResponseEntity.ok("Codigo Válido");
        }else{
            return ResponseEntity.ok("Erro ao validar codigo");
        }
    }



}
