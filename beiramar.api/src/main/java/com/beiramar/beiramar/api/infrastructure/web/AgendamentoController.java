package com.beiramar.beiramar.api.infrastructure.web;

import com.beiramar.beiramar.api.core.application.command.agendamentocommand.AgendamentoAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.agendamentocommand.AgendamentoCadastroCommand;
import com.beiramar.beiramar.api.core.application.usecase.agendamentousecase.*;
import com.beiramar.beiramar.api.core.domain.Agendamento;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Agendamentos", description = "Endpoints relacionados a agendamentos")
public class AgendamentoController {


    private final CadastrarAgendamentoUseCase cadastrarUseCase;
    private final AtualizarAgendamentoUseCase atualizarUseCase;
    private final BuscarAgendamentoPorIdUseCase buscarPorIdUseCase;
    private final DeletarAgendamentoUseCase deletarUseCase;
    private final ListarAgendamentosUseCase listarUseCase;
    private final ListarAgendamentosPorIdClienteUseCase listarPorClienteUseCase;
    private final ListarAgendamentosPorMesUseCase listarPorMesUseCase;

    public AgendamentoController(
            CadastrarAgendamentoUseCase cadastrarUseCase,
            AtualizarAgendamentoUseCase atualizarUseCase,
            BuscarAgendamentoPorIdUseCase buscarPorIdUseCase,
            DeletarAgendamentoUseCase deletarUseCase,
            ListarAgendamentosUseCase listarUseCase,
            ListarAgendamentosPorIdClienteUseCase listarPorClienteUseCase,
            ListarAgendamentosPorMesUseCase listarPorMesUseCase
    ) {
        this.cadastrarUseCase = cadastrarUseCase;
        this.atualizarUseCase = atualizarUseCase;
        this.buscarPorIdUseCase = buscarPorIdUseCase;
        this.deletarUseCase = deletarUseCase;
        this.listarUseCase = listarUseCase;
        this.listarPorClienteUseCase = listarPorClienteUseCase;
        this.listarPorMesUseCase = listarPorMesUseCase;
    }

    @PostMapping
    public ResponseEntity<Agendamento> cadastrar(@RequestBody AgendamentoCadastroCommand command) {
        Agendamento agendamento = cadastrarUseCase.executar(command);
        return ResponseEntity.status(201).body(agendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable Integer id,
                                                 @RequestBody AgendamentoAtualizacaoCommand command) {
        Agendamento atualizado = atualizarUseCase.executar(id, command);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Integer id) {
        Agendamento agendamento = buscarPorIdUseCase.executar(id);
        return ResponseEntity.ok(agendamento);
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listar() {
        return ResponseEntity.ok(listarUseCase.executar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Agendamento>> listarPorCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(listarPorClienteUseCase.executar(idCliente));
    }

    @GetMapping("/mes")
    public ResponseEntity<List<Agendamento>> listarPorMes(@RequestParam Integer mes,
                                                          @RequestParam Integer ano) {
        return ResponseEntity.ok(listarPorMesUseCase.executar(mes, ano));
    }

}
