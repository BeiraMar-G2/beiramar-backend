package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.ServicoCadastroDto;
import com.beiramar.beiramar.api.dto.ServicoListagemDto;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@Tag(name = "Serviços", description = "Endpoints relacionados a serviços")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(com.beiramar.beiramar.api.service.ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @Operation(summary = "Cadastro de serviço")
    @PostMapping
    public ResponseEntity<ServicoListagemDto> cadastrar(@RequestBody @Valid ServicoCadastroDto dto) {

        ServicoListagemDto servicoCriado = servicoService.cadastrarServico(dto);
        return ResponseEntity.status(201).body(servicoCriado);
    }

    @Operation(summary = "Listagem de serviço")
    @GetMapping
    public ResponseEntity<List<ServicoListagemDto>> listar() {
        List<ServicoListagemDto> servicos = servicoService.listarTodosServicos();

        if (servicos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(servicos);
    }

    @Operation(summary = "Buscar serviço por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ServicoListagemDto> buscarPorId(@PathVariable Integer id) {
        try {
            ServicoListagemDto servico = servicoService.buscarServicoPorId(id);
            return ResponseEntity.status(200).body(servico);
        }
        catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @Operation(summary = "Atualizar serviço")
    @PutMapping("/{id}")
    public ResponseEntity<ServicoListagemDto> atualizar(@PathVariable Integer id, @RequestBody ServicoCadastroDto dto) {
        try {
            ServicoListagemDto atualizado = servicoService.atualizarServico(id, dto);
            return ResponseEntity.status(200).body(atualizado);
        }
        catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @Operation(summary = "Deletar serviço")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            servicoService.deletarServico(id);
            return ResponseEntity.status(204).build();
        }
        catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
