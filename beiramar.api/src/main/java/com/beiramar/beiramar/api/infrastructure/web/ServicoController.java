package com.beiramar.beiramar.api.infrastructure.web;

import com.beiramar.beiramar.api.core.application.command.servicocommand.ServicoAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.servicocommand.ServicoCadastroCommand;
import com.beiramar.beiramar.api.core.application.usecase.servicousecase.*;
import com.beiramar.beiramar.api.core.domain.Servico;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@Tag(name = "Serviços", description = "Endpoints relacionados a serviços")
public class ServicoController {

    private final CadastrarServicoUseCase cadastrarServicoUseCase;
    private final ListarServicosUseCase listarServicosUseCase;
    private final BuscarServicoPorIdUseCase buscarServicoPorIdUseCase;
    private final AtualizarServicoUseCase atualizarServicoUseCase;
    private final DeletarServicoUseCase deletarServicoUseCase;

    public ServicoController(CadastrarServicoUseCase cadastrarServicoUseCase,
                             ListarServicosUseCase listarServicosUseCase,
                             BuscarServicoPorIdUseCase buscarServicoPorIdUseCase,
                             AtualizarServicoUseCase atualizarServicoUseCase,
                             DeletarServicoUseCase deletarServicoUseCase) {
        this.cadastrarServicoUseCase = cadastrarServicoUseCase;
        this.listarServicosUseCase = listarServicosUseCase;
        this.buscarServicoPorIdUseCase = buscarServicoPorIdUseCase;
        this.atualizarServicoUseCase = atualizarServicoUseCase;
        this.deletarServicoUseCase = deletarServicoUseCase;
    }

    @PostMapping
    public ResponseEntity<Servico> cadastrar(@RequestBody ServicoCadastroCommand command) {
        Servico servico = cadastrarServicoUseCase.executar(command);
        return ResponseEntity.status(201).body(servico);
    }

    @GetMapping
    public ResponseEntity<List<Servico>> listar() {
        return ResponseEntity.ok(listarServicosUseCase.executar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(buscarServicoPorIdUseCase.executar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Integer id,
                                             @RequestBody ServicoAtualizacaoCommand command) {
        return ResponseEntity.ok(atualizarServicoUseCase.executar(id, command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarServicoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

}
