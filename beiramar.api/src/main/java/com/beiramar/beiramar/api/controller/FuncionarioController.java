package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.FuncionarioListagemDto;
import com.beiramar.beiramar.api.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@Tag(name = "Funcionários", description = "Endpoints relacionados a funcionários")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Operation(summary = "Cadastro de funcionário")
    @PostMapping
    public ResponseEntity<FuncionarioListagemDto> cadastrar(@RequestBody @Valid FuncionarioCadastroDto dto) {
        FuncionarioListagemDto funcionarioCadastrado = funcionarioService.cadastrarFuncionario(dto);
        return ResponseEntity.status(201).body(funcionarioCadastrado);
    }

    @Operation(summary = "Listagem de funcionários")
    @GetMapping
    public ResponseEntity<List<FuncionarioListagemDto>> listar() {
        List<FuncionarioListagemDto> funcionarios = funcionarioService.listarTodosFuncionarios();

        if (funcionarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(funcionarios);
    }
}
