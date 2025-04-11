package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.FuncionarioListagemDto;
import com.beiramar.beiramar.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioListagemDto> cadastrarFuncionario(@Valid @RequestBody FuncionarioCadastroDto funcionarioParaCadastrar){
        FuncionarioListagemDto funcionarioCadastrado = usuarioService.cadastrar(funcionarioParaCadastrar);
        return ResponseEntity.status(201).body(funcionarioCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioListagemDto>> listarFuncionarios(){
        List<FuncionarioListagemDto> funcionarios = usuarioService.listarTodos();

        if (funcionarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(funcionarios);
    }
}
