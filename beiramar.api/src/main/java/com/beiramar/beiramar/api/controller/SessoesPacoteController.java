package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoAtualizacaoDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoCadastroDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoListagemDto;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteCadastroDto;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteListagemDto;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.SessoesPacoteRepository;
import com.beiramar.beiramar.api.service.SessoesPacoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessoes")
@Tag(name = "SessoesPacote", description = "Endpoints relacionados a SessoesPacote")
public class SessoesPacoteController {

    private final SessoesPacoteService sessoesPacoteService;

    public SessoesPacoteController(SessoesPacoteService sessoesPacoteService) {
        this.sessoesPacoteService = sessoesPacoteService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar Sessoes")
    public ResponseEntity<SessoesPacoteListagemDto> cadastrar(@RequestBody SessoesPacoteCadastroDto dto) {
        return ResponseEntity.status(201).body(sessoesPacoteService.cadastrar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar Sessoes")
    public ResponseEntity<List<SessoesPacoteListagemDto>> listarTodos() {
        List<SessoesPacoteListagemDto> sessoes = sessoesPacoteService.listarTodos();

        if (sessoes.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(sessoes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Sessao por ID")
    public ResponseEntity<SessoesPacoteListagemDto> buscar(@PathVariable Integer id) {
        try {
            SessoesPacoteListagemDto sessoes = sessoesPacoteService.buscarPorId(id);
            return ResponseEntity.status(200).body(sessoes);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Sessao")
    public ResponseEntity<SessoesPacoteListagemDto> atualizar(@PathVariable Integer id, @RequestBody SessoesPacoteAtualizacaoDto dto) {
        try {
            SessoesPacoteListagemDto sessoes = sessoesPacoteService.atualizar(id, dto);
            return ResponseEntity.status(200).body(sessoes);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar agendamento")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            sessoesPacoteService.deletar(id);
            return ResponseEntity.status(204).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
