package com.beiramar.beiramar.api.infrastructure.web;

import com.beiramar.beiramar.api.core.application.command.disponibilidadecommand.DisponibilidadeAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.disponibilidadecommand.DisponibilidadeCadastroCommand;
import com.beiramar.beiramar.api.core.application.usecase.disponibilidadeusecase.*;
import com.beiramar.beiramar.api.core.domain.Disponibilidade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disponibilidades")
@Tag(name = "Disponibilidade", description = "Endpoints relacionados a disponibilidade")
public class DisponibilidadeController {


    private final CadastrarDisponibilidadeUseCase cadastrarDisponibilidadeUseCase;
    private final AtualizarDisponibilidadeUseCase atualizarDisponibilidadeUseCase;
    private final BuscarDisponibilidadePorIdUseCase buscarDisponibilidadePorIdUseCase;
    private final DeletarDisponibilidadeUseCase deletarDisponibilidadeUseCase;
    private final ListarDisponibilidadesUseCase listarDisponibilidadesUseCase;

    public DisponibilidadeController(CadastrarDisponibilidadeUseCase cadastrarDisponibilidadeUseCase, AtualizarDisponibilidadeUseCase atualizarDisponibilidadeUseCase, BuscarDisponibilidadePorIdUseCase buscarDisponibilidadePorIdUseCase, DeletarDisponibilidadeUseCase deletarDisponibilidadeUseCase, ListarDisponibilidadesUseCase listarDisponibilidadesUseCase) {
        this.cadastrarDisponibilidadeUseCase = cadastrarDisponibilidadeUseCase;
        this.atualizarDisponibilidadeUseCase = atualizarDisponibilidadeUseCase;
        this.buscarDisponibilidadePorIdUseCase = buscarDisponibilidadePorIdUseCase;
        this.deletarDisponibilidadeUseCase = deletarDisponibilidadeUseCase;
        this.listarDisponibilidadesUseCase = listarDisponibilidadesUseCase;
    }

    @PostMapping
    @Operation(summary = "Cadastrar Disponibilidade")
    public ResponseEntity<Disponibilidade> cadastrar(@RequestBody DisponibilidadeCadastroCommand command) {
        Disponibilidade disponibilidade = cadastrarDisponibilidadeUseCase.executar(command);
        return ResponseEntity.status(201).body(disponibilidade);
    }

    @GetMapping
    @Operation(summary = "Listar Disponibilidade")
    public ResponseEntity<List<Disponibilidade>> listarTodos() {
        return ResponseEntity.ok(listarDisponibilidadesUseCase.executar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Disponibilidade por ID")
    public ResponseEntity<Disponibilidade> buscar(@PathVariable Integer id) {
        Disponibilidade disponibilidade = buscarDisponibilidadePorIdUseCase.executar(id);
        return ResponseEntity.ok(disponibilidade);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Disponibilidade")
    public ResponseEntity<Disponibilidade> atualizar(@PathVariable Integer id, @RequestBody DisponibilidadeAtualizacaoCommand command) {
        Disponibilidade atualizado = atualizarDisponibilidadeUseCase.executar(id, command);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Disponibilidade")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarDisponibilidadeUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

}
