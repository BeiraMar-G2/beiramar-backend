package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteCadastroDto;
import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteListagemDto;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.service.ValorPacoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valores-pacote")
@Tag(name = "ValorPacote", description = "Endpoints relacionados a ValorPacote")
public class ValorPacoteController {

    private final ValorPacoteService valorPacoteService;

    public ValorPacoteController(ValorPacoteService valorPacoteService) {
        this.valorPacoteService = valorPacoteService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar valorPacote")
    public ResponseEntity<ValorPacoteListagemDto> cadastrar(@RequestBody ValorPacoteCadastroDto dto) {
        return ResponseEntity.status(201).body(valorPacoteService.cadatrar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar valorPacote")
    public ResponseEntity<List<ValorPacoteListagemDto>> listarTodos() {
        List<ValorPacoteListagemDto> valores = valorPacoteService.listarTodos();

        if (valores.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(valores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar valorPacote por ID")
    public ResponseEntity<ValorPacoteListagemDto> buscar(@PathVariable Integer id) {
        try {
            ValorPacoteListagemDto valores = valorPacoteService.buscarPorId(id);
            return ResponseEntity.status(200).body(valores);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ValorPacote")
    public ResponseEntity<ValorPacoteListagemDto> atualizar(@PathVariable Integer id, @RequestBody ValorPacoteAtualizacaoDto dto) {
        try {
            ValorPacoteListagemDto valores = valorPacoteService.atualizar(id, dto);
            return ResponseEntity.status(200).body(valores);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar valorPacote")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            valorPacoteService.deletar(id);
            return ResponseEntity.status(204).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }


}
