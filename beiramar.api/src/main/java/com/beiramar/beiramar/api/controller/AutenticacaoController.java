package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.UsuarioLoginDto;
import com.beiramar.beiramar.api.dto.UsuarioTokenDto;
import com.beiramar.beiramar.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacoes")
@Tag(name = "Login", description = "Endpoint relacionado a autenticação do usuario")
public class AutenticacaoController {

    private final UsuarioService usuarioService;

    public AutenticacaoController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto dto) {
        return ResponseEntity.status(200).body(usuarioService.autenticar(dto));
    }
}
