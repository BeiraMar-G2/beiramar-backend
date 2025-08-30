package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteCadastroDto;
import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteListagemDto;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.service.PacoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacotes")
@Tag(name = "Pacotes", description = "Endpoints relacionados a pacotes")
public class PacoteController {

    private final PacoteService pacoteService;

    public PacoteController(PacoteService pacoteService) {
        this.pacoteService = pacoteService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar pacote")
    public ResponseEntity<PacoteListagemDto> cadastrar(@RequestBody @Valid PacoteCadastroDto dto) {
        return ResponseEntity.status(201).body(pacoteService.cadastrar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar todos os pacotes")
    public ResponseEntity<List<PacoteListagemDto>> listarTodos() {
        List<PacoteListagemDto> pacotes = pacoteService.listarTodos();

        if (pacotes.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(pacotes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pacote por ID")
    public ResponseEntity<PacoteListagemDto> buscarPorId(@PathVariable Integer id) {
        try {
            PacoteListagemDto pacote = pacoteService.buscarPorId(id);
            return ResponseEntity.status(200).body(pacote);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pacote por ID")
    public ResponseEntity<PacoteListagemDto> atualizar(@PathVariable Integer id, @RequestBody @Valid PacoteAtualizacaoDto dto) {
        try {
            PacoteListagemDto atulizado = pacoteService.atualizar(id, dto);
            return ResponseEntity.status(200).body(atulizado);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar pacote por ID")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            pacoteService.deletar(id);
            return ResponseEntity.status(204).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
