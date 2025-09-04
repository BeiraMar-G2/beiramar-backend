package com.beiramar.beiramar.api.infrastructure.web;

import com.beiramar.beiramar.api.core.application.command.valorpacotecommand.ValorPacoteAtulizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.valorpacotecommand.ValorPacoteCadastroCommand;
import com.beiramar.beiramar.api.core.application.usecase.valorpacoteusecase.*;
import com.beiramar.beiramar.api.core.domain.ValorPacote;
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


    private final CadastrarValorPacoteUseCase cadastrarUseCase;
    private final AtualizarValorPacoteUseCase atualizarUseCase;
    private final BuscarValorPacotePorIdUseCase buscarPorIdUseCase;
    private final ListarValorPacoteUseCase listarUseCase;
    private final DeletarValorPacoteUseCase deletarUseCase;

    public ValorPacoteController(
            CadastrarValorPacoteUseCase cadastrarUseCase,
            AtualizarValorPacoteUseCase atualizarUseCase,
            BuscarValorPacotePorIdUseCase buscarPorIdUseCase,
            ListarValorPacoteUseCase listarUseCase,
            DeletarValorPacoteUseCase deletarUseCase) {
        this.cadastrarUseCase = cadastrarUseCase;
        this.atualizarUseCase = atualizarUseCase;
        this.buscarPorIdUseCase = buscarPorIdUseCase;
        this.listarUseCase = listarUseCase;
        this.deletarUseCase = deletarUseCase;
    }

    @PostMapping
    public ResponseEntity<ValorPacote> cadastrar(@RequestBody ValorPacoteCadastroCommand command) {
        ValorPacote valorPacote = cadastrarUseCase.executar(command);
        return ResponseEntity.status(201).body(valorPacote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ValorPacote> atualizar(@PathVariable Integer id,
                                                 @RequestBody ValorPacoteAtulizacaoCommand command) {
        ValorPacote atualizado = atualizarUseCase.executar(id, command);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValorPacote> buscarPorId(@PathVariable Integer id) {
        ValorPacote valorPacote = buscarPorIdUseCase.executar(id);
        return ResponseEntity.ok(valorPacote);
    }

    @GetMapping
    public ResponseEntity<List<ValorPacote>> listar() {
        return ResponseEntity.ok(listarUseCase.executar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

}
