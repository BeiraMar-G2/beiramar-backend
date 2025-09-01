package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.autenticacaoDtos.UsuarioLoginDto;
import com.beiramar.beiramar.api.dto.autenticacaoDtos.UsuarioTokenDto;
import com.beiramar.beiramar.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacoes")
@Tag(name = "Login", description = "Endpoint relacionado a autenticação do usuario")
public class AutenticacaoController {

    private final UsuarioService usuarioService;

    public AutenticacaoController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Dados do cliente a ser cadastrado",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = UsuarioLoginDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "Exemplo de funcionário válido",
                                    summary = "Exemplo de funcionário válido",
                                    value = "{ \"email\": \"ana@gmail.com\", \"senha\": \"BeiraMar321\" }"
                            )
                    }))@RequestBody UsuarioLoginDto dto) {
        return ResponseEntity.status(200).body(usuarioService.autenticar(dto));
    }
}
