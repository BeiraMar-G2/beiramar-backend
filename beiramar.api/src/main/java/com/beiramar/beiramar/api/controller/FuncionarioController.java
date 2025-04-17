package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.FuncionarioListagemDto;
import com.beiramar.beiramar.api.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioListagemDto> cadastrar(@RequestBody @Valid FuncionarioCadastroDto dto) {
        FuncionarioListagemDto funcionarioCadastrado = funcionarioService.cadastrar(dto);
        return ResponseEntity.status(201).body(funcionarioCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioListagemDto>> listar() {
        List<FuncionarioListagemDto> funcionarios = funcionarioService.listarTodos();

        if (funcionarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(funcionarios);
    }
}
