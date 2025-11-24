package com.beiramar.beiramar.api.infrastructure.web;

import com.beiramar.beiramar.api.core.application.command.agendamentocommand.AgendamentoAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.agendamentocommand.AgendamentoCadastroCommand;
import com.beiramar.beiramar.api.core.application.usecase.agendamentousecase.*;
import com.beiramar.beiramar.api.core.domain.Agendamento;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    private final ListarAgendamentosPaginadoUseCase listarPaginadoUseCase;
    private final ListarAgendamentosPorIdClienteUseCase listarPorClienteUseCase;
    private final ListarAgendamentosPorIdClientePaginadoUseCase listarPorClientePaginadoUseCase;
    private final ListarAgendamentosPorMesUseCase listarPorMesUseCase;
    private final ListarAgendamentosPorMesPaginadoUseCase listarPorMesPaginadoUseCase;
    private final ListarAgendamentosHistoricoUseCase listarHistoricoUseCase;
    private final ListarAgendamentosHistoricoPaginadoUseCase listarHistoricoPaginadoUseCase;
    private final ContarAgendamentoStatusAgendadoUseCase contarAgendamentoUseCase;
    private final ContarAgendamentoStatusCanceladoUseCase contarCanceladosUseCase;

    public AgendamentoController(CadastrarAgendamentoUseCase cadastrarUseCase, AtualizarAgendamentoUseCase atualizarUseCase, BuscarAgendamentoPorIdUseCase buscarPorIdUseCase, DeletarAgendamentoUseCase deletarUseCase, ListarAgendamentosUseCase listarUseCase, ListarAgendamentosPaginadoUseCase listarPaginadoUseCase, ListarAgendamentosPorIdClienteUseCase listarPorClienteUseCase, ListarAgendamentosPorIdClientePaginadoUseCase listarPorClientePaginadoUseCase, ListarAgendamentosPorMesUseCase listarPorMesUseCase, ListarAgendamentosPorMesPaginadoUseCase listarPorMesPaginadoUseCase, ListarAgendamentosHistoricoUseCase listarHistoricoUseCase, ListarAgendamentosHistoricoPaginadoUseCase listarHistoricoPaginadoUseCase, ContarAgendamentoStatusAgendadoUseCase contarAgendamentoUseCase, ContarAgendamentoStatusCanceladoUseCase contarCanceladosUseCase) {
        this.cadastrarUseCase = cadastrarUseCase;
        this.atualizarUseCase = atualizarUseCase;
        this.buscarPorIdUseCase = buscarPorIdUseCase;
        this.deletarUseCase = deletarUseCase;
        this.listarUseCase = listarUseCase;
        this.listarPaginadoUseCase = listarPaginadoUseCase;
        this.listarPorClienteUseCase = listarPorClienteUseCase;
        this.listarPorClientePaginadoUseCase = listarPorClientePaginadoUseCase;
        this.listarPorMesUseCase = listarPorMesUseCase;
        this.listarPorMesPaginadoUseCase = listarPorMesPaginadoUseCase;
        this.listarHistoricoUseCase = listarHistoricoUseCase;
        this.listarHistoricoPaginadoUseCase = listarHistoricoPaginadoUseCase;
        this.contarAgendamentoUseCase = contarAgendamentoUseCase;
        this.contarCanceladosUseCase = contarCanceladosUseCase;
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


    @GetMapping("/paginado")
    public ResponseEntity<Page<Agendamento>> listarTodosPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Agendamento> agendamentos = listarPaginadoUseCase.executar(pageable);
        return ResponseEntity.ok(agendamentos);
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

    @GetMapping("/cliente/{idCliente}/paginado")
    public ResponseEntity<Page<Agendamento>> listarPorClientePaginado(
            @PathVariable Integer idCliente,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Agendamento> agendamentos = listarPorClientePaginadoUseCase.executar(idCliente, pageable);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/mes")
    public ResponseEntity<List<Agendamento>> listarPorMes(@RequestParam Integer mes,
                                                          @RequestParam Integer ano) {
        return ResponseEntity.ok(listarPorMesUseCase.executar(mes, ano));
    }

    @GetMapping("/mes/{mes}/{ano}/paginado")
    public ResponseEntity<Page<Agendamento>> listarPorMesPaginado(
            @PathVariable int mes,
            @PathVariable int ano,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Agendamento> agendamentos = listarPorMesPaginadoUseCase.executar(mes, ano, pageable);
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/historico")
    public ResponseEntity<List<Agendamento>> listarHistorico(
            @RequestParam("idCliente") Integer idCliente,
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data
    ) {
        return ResponseEntity.ok(listarHistoricoUseCase.executar(idCliente, data));
    }

    @GetMapping("/historico/paginado")
    public ResponseEntity<Page<Agendamento>> listarHistoricoPaginado(
            @RequestParam("idCliente") Integer idCliente,
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(listarHistoricoPaginadoUseCase.executar(idCliente, data, pageable));
    }

    @GetMapping("/contarAgendados")
    public ResponseEntity<Long> contarAgendamentos(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        Long quantidade = contarAgendamentoUseCase.executar(inicio, fim);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/contarCancelados")
    public ResponseEntity<Long> contarAgendamentosCancelados(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        Long quantidade = contarCanceladosUseCase.executar(inicio, fim);
        return ResponseEntity.ok(quantidade);
    }

}
