package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoAtualizacaoDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoCadastroDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoListagemDto;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Agendamentos", description = "Endpoints relacionados a agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar agendamento")
    public ResponseEntity<AgendamentoListagemDto> cadastrar(@RequestBody AgendamentoCadastroDto dto) {
        return ResponseEntity.status(201).body(agendamentoService.cadastrar(dto));
    }


    @GetMapping
    @Operation(summary = "Listar todos agendamentos")
    public ResponseEntity<List<AgendamentoListagemDto>> listarTodos() {
        List<AgendamentoListagemDto> agendamento = agendamentoService.listarTodos();

        if (agendamento.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(agendamento);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar agendamento por ID")
    public ResponseEntity<AgendamentoListagemDto> buscar(@PathVariable Integer id) {
        try {
            AgendamentoListagemDto agendamento = agendamentoService.buscarPorId(id);
            return ResponseEntity.status(200).body(agendamento);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar agendamento")
    public ResponseEntity<AgendamentoListagemDto> atualizar(@PathVariable Integer id, @RequestBody AgendamentoAtualizacaoDto dto) {
        try {
            AgendamentoListagemDto atualizado = agendamentoService.atualizar(id, dto);
            return ResponseEntity.status(200).body(atualizado);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar agendamento")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            agendamentoService.deletar(id);
            return ResponseEntity.status(204).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/mes")
    @Operation(summary = "Listar agendamentos por mês")
    public ResponseEntity<List<AgendamentoListagemDto>> listarPorMes(
            @RequestParam int ano,
            @RequestParam int mes) {
        List<AgendamentoListagemDto> agendamentos = agendamentoService.listarPorMes(ano, mes);

        if (agendamentos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(agendamentos);
    }

}
