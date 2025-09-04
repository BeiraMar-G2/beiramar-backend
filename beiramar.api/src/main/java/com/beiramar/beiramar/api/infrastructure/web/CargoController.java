package com.beiramar.beiramar.api.infrastructure.web;

import com.beiramar.beiramar.api.core.application.command.cargocommand.CargoAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.cargocommand.CargoCadastroCommand;
import com.beiramar.beiramar.api.core.application.usecase.cargousecase.*;
import com.beiramar.beiramar.api.core.domain.Cargo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargos")
@Tag(name = "Cargo", description = "Endpoints relacionados a cargo")
public class CargoController {


    private final CadastrarCargoUseCase cadastrarCargoUseCase;
    private final ListarCargosUseCase listarCargosUseCase;
    private final BuscarCargoPorIdUseCase buscarCargoPorIdUseCase;
    private final AtualizarCargoUseCase atualizarCargoUseCase;
    private final DeletarCargoUseCase deletarCargoUseCase;

    public CargoController(CadastrarCargoUseCase cadastrarCargoUseCase,
                           ListarCargosUseCase listarCargosUseCase,
                           BuscarCargoPorIdUseCase buscarCargoPorIdUseCase,
                           AtualizarCargoUseCase atualizarCargoUseCase,
                           DeletarCargoUseCase deletarCargoUseCase) {
        this.cadastrarCargoUseCase = cadastrarCargoUseCase;
        this.listarCargosUseCase = listarCargosUseCase;
        this.buscarCargoPorIdUseCase = buscarCargoPorIdUseCase;
        this.atualizarCargoUseCase = atualizarCargoUseCase;
        this.deletarCargoUseCase = deletarCargoUseCase;
    }

    @PostMapping
    public ResponseEntity<Cargo> cadastrar(@RequestBody CargoCadastroCommand command) {
        Cargo cargo = cadastrarCargoUseCase.executar(command);
        return ResponseEntity.status(201).body(cargo);
    }

    @GetMapping
    public ResponseEntity<List<Cargo>> listar() {
        return ResponseEntity.ok(listarCargosUseCase.executar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(buscarCargoPorIdUseCase.executar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargo> atualizar(@PathVariable Integer id,
                                           @RequestBody CargoAtualizacaoCommand command) {
        return ResponseEntity.ok(atualizarCargoUseCase.executar(id, command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarCargoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
