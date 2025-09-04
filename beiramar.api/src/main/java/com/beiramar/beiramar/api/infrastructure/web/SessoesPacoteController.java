package com.beiramar.beiramar.api.infrastructure.web;

import com.beiramar.beiramar.api.core.application.command.sessoespacotecommand.SessoesPacoteAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.sessoespacotecommand.SessoesPacoteCadastroCommand;
import com.beiramar.beiramar.api.core.application.usecase.sessoespacoteusecase.*;
import com.beiramar.beiramar.api.core.domain.SessoesPacote;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessoes")
@Tag(name = "SessoesPacote", description = "Endpoints relacionados a SessoesPacote")
public class SessoesPacoteController {


    private final CadastrarSessoesPacoteUseCase cadastrarUseCase;
    private final AtualizarSessoesPacoteUseCase atualizarUseCase;
    private final BuscarSessoesPacotePorIdUseCase buscarPorIdUseCase;
    private final ListarSessoesPacoteUseCase listarUseCase;
    private final DeletarSessoesPacoteUseCase deletarUseCase;

    public SessoesPacoteController(CadastrarSessoesPacoteUseCase cadastrarUseCase, AtualizarSessoesPacoteUseCase atualizarUseCase, BuscarSessoesPacotePorIdUseCase buscarPorIdUseCase, ListarSessoesPacoteUseCase listarUseCase, DeletarSessoesPacoteUseCase deletarUseCase) {
        this.cadastrarUseCase = cadastrarUseCase;
        this.atualizarUseCase = atualizarUseCase;
        this.buscarPorIdUseCase = buscarPorIdUseCase;
        this.listarUseCase = listarUseCase;
        this.deletarUseCase = deletarUseCase;
    }

    @PostMapping
    public ResponseEntity<SessoesPacote> cadastrar(@RequestBody SessoesPacoteCadastroCommand command) {
        SessoesPacote sessoesPacote = cadastrarUseCase.executar(command);
        return ResponseEntity.status(201).body(sessoesPacote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessoesPacote> atualizar(@PathVariable Integer id,
                                                 @RequestBody SessoesPacoteAtualizacaoCommand command) {
        SessoesPacote atualizado = atualizarUseCase.executar(id, command);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessoesPacote> buscarPorId(@PathVariable Integer id) {
        SessoesPacote sessoesPacote = buscarPorIdUseCase.executar(id);
        return ResponseEntity.ok(sessoesPacote);
    }

    @GetMapping
    public ResponseEntity<List<SessoesPacote>> listar() {
        return ResponseEntity.ok(listarUseCase.executar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
