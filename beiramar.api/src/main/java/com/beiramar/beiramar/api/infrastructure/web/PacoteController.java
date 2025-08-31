package com.beiramar.beiramar.api.infrastructure.web;

import com.beiramar.beiramar.api.core.application.command.pacotecommand.PacoteAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.pacotecommand.PacoteCadastroCommand;
import com.beiramar.beiramar.api.core.application.usecase.pacoteusecase.*;
import com.beiramar.beiramar.api.core.domain.Pacote;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacotes")
@Tag(name = "Pacotes", description = "Endpoints relacionados a pacotes")
public class PacoteController {

    private final CadastrarPacoteUseCase cadastrarPacoteUseCase;
    private final ListarPacotesUseCase listarPacotesUseCase;
    private final AtualizarPacoteUseCase atualizarPacoteUseCase;
    private final BuscarPacotePorIdUseCase buscarPacotePorIdUseCase;
    private final DeletarPacoteUseCase deletarPacoteUseCase;

    public PacoteController(CadastrarPacoteUseCase cadastrarPacoteUseCase,
                            ListarPacotesUseCase listarPacotesUseCase,
                            AtualizarPacoteUseCase atualizarPacoteUseCase,
                            BuscarPacotePorIdUseCase buscarPacotePorIdUseCase,
                            DeletarPacoteUseCase deletarPacoteUseCase) {
        this.cadastrarPacoteUseCase = cadastrarPacoteUseCase;
        this.listarPacotesUseCase = listarPacotesUseCase;
        this.atualizarPacoteUseCase = atualizarPacoteUseCase;
        this.buscarPacotePorIdUseCase = buscarPacotePorIdUseCase;
        this.deletarPacoteUseCase = deletarPacoteUseCase;
    }

    @PostMapping
    public ResponseEntity<Pacote> cadastrar(@RequestBody PacoteCadastroCommand command) {
        Pacote pacote = cadastrarPacoteUseCase.executar(command);
        return ResponseEntity.status(201).body(pacote);
    }

    @GetMapping
    public ResponseEntity<List<Pacote>> listar() {
        return ResponseEntity.ok(listarPacotesUseCase.executar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pacote> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(buscarPacotePorIdUseCase.executar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pacote> atualizar(@PathVariable Integer id,
                                             @RequestBody PacoteAtualizacaoCommand command) {
        return ResponseEntity.ok(atualizarPacoteUseCase.executar(id, command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarPacoteUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
