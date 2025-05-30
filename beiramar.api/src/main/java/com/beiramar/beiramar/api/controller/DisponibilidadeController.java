package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.disponibilidadeDtos.DisponibilidadeAtualizacaoDto;
import com.beiramar.beiramar.api.dto.disponibilidadeDtos.DisponibilidadeCadastroDto;
import com.beiramar.beiramar.api.dto.disponibilidadeDtos.DisponibilidadeListagemDto;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.service.DisponibilidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disponibilidades")
@Tag(name = "Disponibilidade", description = "Endpoints relacionados a disponibilidade")
public class DisponibilidadeController {

    private final DisponibilidadeService disponibilidadeService;

    public DisponibilidadeController(DisponibilidadeService disponibilidadeService) {
        this.disponibilidadeService = disponibilidadeService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar Disponibilidade")
    public ResponseEntity<DisponibilidadeListagemDto> cadastrar(@RequestBody DisponibilidadeCadastroDto dto) {
        return ResponseEntity.status(201).body(disponibilidadeService.cadastrar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar Disponibilidade")
    public ResponseEntity<List<DisponibilidadeListagemDto>> listarTodos() {
        List<DisponibilidadeListagemDto> disponibilidades = disponibilidadeService.listarTodos();

        if (disponibilidades.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(disponibilidades);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Disponibilidade por ID")
    public ResponseEntity<DisponibilidadeListagemDto> buscar(@PathVariable Integer id) {
        try {
            DisponibilidadeListagemDto disponibilidades = disponibilidadeService.buscarPorId(id);
            return ResponseEntity.status(200).body(disponibilidades);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Disponibilidade")
    public ResponseEntity<DisponibilidadeListagemDto> atualizar(@PathVariable Integer id, @RequestBody DisponibilidadeAtualizacaoDto dto) {
        try {
            DisponibilidadeListagemDto disponibilidades = disponibilidadeService.atualizar(id, dto);
            return ResponseEntity.status(200).body(disponibilidades);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Disponibilidade")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            disponibilidadeService.deletar(id);
            return ResponseEntity.status(204).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

}
