package com.beiramar.beiramar.api.infrastructure.features.controller;

import com.beiramar.beiramar.api.infrastructure.features.dto.notificacaoDtos.CodigoSenhaRequestDTO;
import com.beiramar.beiramar.api.infrastructure.features.dto.notificacaoDtos.EmailRequestDTO;
import com.beiramar.beiramar.api.infrastructure.features.repository.LogSenhaRepository;
import com.beiramar.beiramar.api.infrastructure.features.service.RecuperacaoSenhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recuperacao")
@Tag(name = "Recuperação de senha", description = "Endpoints relacionados a recuperação de senha")
public class RecuperacaoSenhaController {
    @Autowired
    private RecuperacaoSenhaService service;

    @Autowired
    private LogSenhaRepository logSenhaRepository;

    @ApiResponse(responseCode = "201", description = "Código enviado", content = @Content(examples = @ExampleObject("Código enviado com sucesso")))
    @Operation(summary = "Envio de código de recuperação")
    @PostMapping("/enviar-codigo")
    public ResponseEntity<String> enviarCodigo(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Email de recuperação de senha",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = EmailRequestDTO.class),
                    examples = {
                            @ExampleObject(
                                    name = "Exemplo de recuperação de senha",
                                    summary = "Exemplo de recuperação de senha",
                                    value = "{ \"email\": \"vitorhideo.mf@gmail.com\" }"
                            )
                    }))@RequestBody EmailRequestDTO request){
        service.enviarCodigo(request.getEmail());
        return ResponseEntity.status(201).body("Código enviado com sucesso");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Clientes encontrados", content = @Content(examples = @ExampleObject("Código Autenticado"))),
            @ApiResponse(responseCode = "204", description = "Nenhum cliente cadastrado ainda", content = @Content(examples = @ExampleObject("Código Inválido")))
    })
    @Operation(summary = "Validação do código de recuperação")
    @PostMapping("/validar-codigo")
    public ResponseEntity<String> validarCodigo(@RequestBody CodigoSenhaRequestDTO dto){
        boolean isValido = service.validarCodigo(dto.getEmail(), dto.getCodigo());
        if(isValido){
            return ResponseEntity.status(200).body("Código Autenticado");
        }else{
            return ResponseEntity.status(401).body("Código Inválido");
        }
    }



}
