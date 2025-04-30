package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.ClienteListagemDto;
import com.beiramar.beiramar.api.dto.FuncionarioAtualizacaoDto;
import com.beiramar.beiramar.api.dto.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.FuncionarioListagemDto;
import com.beiramar.beiramar.api.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
    public ResponseEntity<FuncionarioListagemDto> cadastrar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados dos funcionarios a ser cadastrado",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = FuncionarioListagemDto.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de Funcionario válido que retorna o código 201",
                                            summary = "Exemplo de funcionario válido",
                                            value = "{ \"nome\": \"Ana\", \"email\": \"ana@gmail.com\", \"telefone\": \"11999999991\", \"cpf\": \"12345678912\", \"senha\": \"BeiraMar321\", \"dtNasc\": \"2000-02-02\" }"
                                    ),
                                    @ExampleObject(
                                            name = "Exemplo de Funcionario inválido (email e telefone) que retorna o código 400",
                                            summary = "Exemplo de funcionario inválido",
                                            value = "{\"id\": \"1\", \"nome\": \"Ana\", \"email\": \"Anagmail.com\", \"telefone\": \"11\"}"
                                    )
                            })) @RequestBody @Valid FuncionarioCadastroDto dto) {
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

    @Operation(summary = "Buscar funcionário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioListagemDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(funcionarioService.buscarPorId(id));
    }

    @Operation(summary = "Atualizar funcionário")
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioListagemDto> atualizar(@PathVariable Integer id, @RequestBody @Valid FuncionarioAtualizacaoDto dto) {
        return ResponseEntity.ok(funcionarioService.atualizarFuncionario(id, dto));
    }

    @Operation(summary = "Deletar funcionário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
