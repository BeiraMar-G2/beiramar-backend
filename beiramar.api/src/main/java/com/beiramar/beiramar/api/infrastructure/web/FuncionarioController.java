package com.beiramar.beiramar.api.infrastructure.web;

import com.beiramar.beiramar.api.core.application.command.usuariocommand.UsuarioAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.usuariocommand.UsuarioCadastroCommand;
import com.beiramar.beiramar.api.core.application.command.usuariocommand.UsuarioListagemCommand;
import com.beiramar.beiramar.api.core.application.usecase.usuariousecase.*;
import com.beiramar.beiramar.api.core.domain.Usuario;
import com.beiramar.beiramar.api.dto.funcionarioDtos.FuncionarioListagemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcionarios")
@Tag(name = "Funcionários", description = "Endpoints relacionados a funcionários")
public class FuncionarioController {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final DeletarUsuarioUseCase deletarUsuarioUseCase;
    private final ListarUsuariosPorCargoUseCase listarUsuariosPorCargoUseCase;

    public FuncionarioController(
            CadastrarUsuarioUseCase cadastrarUsuarioUseCase,
            AtualizarUsuarioUseCase atualizarUsuarioUseCase,
            BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
            DeletarUsuarioUseCase deletarUsuarioUseCase,
            ListarUsuariosPorCargoUseCase listarUsuariosPorCargoUseCase
    ) {
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
        this.listarUsuariosPorCargoUseCase = listarUsuariosPorCargoUseCase;
    }

    @PostMapping
    @Operation(summary = "Cadastrar funcionário")
    public ResponseEntity<UsuarioListagemCommand> cadastrar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados dos funcionarios a ser cadastrado",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = FuncionarioListagemDto.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de Funcionario válido que retorna o código 201",
                                            summary = "Exemplo de funcionario válido",
                                            value = "{ \"nome\": \"Ana\", \"email\": \"ana@gmail.com\", \"telefone\": \"11999999991\", \"senha\": \"BeiraMar321\", \"dtNasc\": \"2000-02-02\" , \"fkCargo\": \"2\" }"
                                    ),
                                    @ExampleObject(
                                            name = "Exemplo de Funcionario inválido (email e telefone) que retorna o código 400",
                                            summary = "Exemplo de funcionario inválido",
                                            value = "{\"id\": \"1\", \"nome\": \"Ana\", \"email\": \"Anagmail.com\", \"telefone\": \"11\"}"
                                    )
                            })) @RequestBody @Valid UsuarioCadastroCommand command) {
        Usuario usuario = cadastrarUsuarioUseCase.executar(command);
        return ResponseEntity.status(201).body(toListagemCommand(usuario));
    }

    @GetMapping
    @Operation(summary = "Listar todos os funcionários")
    public ResponseEntity<List<UsuarioListagemCommand>> listar() {
        List<Usuario> funcionarios = listarUsuariosPorCargoUseCase.executar("FUNCIONARIO");
        if (funcionarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funcionarios.stream().map(this::toListagemCommand).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar funcionário por ID")
    public ResponseEntity<UsuarioListagemCommand> buscarPorId(@PathVariable Integer id) {
        Usuario usuario = buscarUsuarioPorIdUseCase.executar(id);
        return ResponseEntity.ok(toListagemCommand(usuario));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar funcionário")
    public ResponseEntity<UsuarioListagemCommand> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid UsuarioAtualizacaoCommand command
    ) {
        Usuario atualizado = atualizarUsuarioUseCase.executar(id, command);
        return ResponseEntity.ok(toListagemCommand(atualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar funcionário")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    private UsuarioListagemCommand toListagemCommand(Usuario usuario) {
        return new UsuarioListagemCommand(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getDtNasc(),
                usuario.getCargo().getNome()
        );
    }
}
