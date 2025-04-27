package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.ServicoCadastroDto;
import com.beiramar.beiramar.api.dto.ServicoListagemDto;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(com.beiramar.beiramar.api.service.ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @PostMapping
    public ResponseEntity<ServicoListagemDto> cadastrar(@RequestBody @Valid ServicoCadastroDto dto) {

        ServicoListagemDto servicoCriado = servicoService.cadastrarServico(dto);
        return ResponseEntity.status(201).body(servicoCriado);
    }

    @GetMapping
    public ResponseEntity<List<ServicoListagemDto>> listar() {
        List<ServicoListagemDto> servicos = servicoService.listarTodosServicos();

        if (servicos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(servicos);
    }

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
