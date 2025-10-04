package com.beiramar.beiramar.api.infrastructure.web;

import com.beiramar.beiramar.api.controller.FileController;
import com.beiramar.beiramar.api.core.application.command.usuariocommand.UsuarioAtualizacaoCommand;
import com.beiramar.beiramar.api.core.application.command.usuariocommand.UsuarioCadastroCommand;
import com.beiramar.beiramar.api.core.application.command.usuariocommand.UsuarioListagemCommand;
import com.beiramar.beiramar.api.core.application.usecase.usuariousecase.*;
import com.beiramar.beiramar.api.core.domain.Usuario;
import com.beiramar.beiramar.api.dto.clienteDtos.ClienteCadastroDto;
import com.beiramar.beiramar.api.entity.FilesEntity;
import com.beiramar.beiramar.api.repository.FilesEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Endpoints relacionados a clientes")
public class ClienteController {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final DeletarUsuarioUseCase deletarUsuarioUseCase;
    private final ListarUsuariosPorCargoUseCase listarUsuariosPorCargoUseCase;
    private final AtualizarFotoUsuarioUseCase atualizarFotoUsuarioUseCase;
    private final FilesEntityRepository filesEntityRepository;

    public ClienteController(
            CadastrarUsuarioUseCase cadastrarUsuarioUseCase,
            AtualizarUsuarioUseCase atualizarUsuarioUseCase,
            BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase,
            DeletarUsuarioUseCase deletarUsuarioUseCase,
            ListarUsuariosPorCargoUseCase listarUsuariosPorCargoUseCase, AtualizarFotoUsuarioUseCase atualizarFotoUsuarioUseCase, FilesEntityRepository filesEntityRepository, FileController fileController
    ) {
        this.cadastrarUsuarioUseCase = cadastrarUsuarioUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
        this.listarUsuariosPorCargoUseCase = listarUsuariosPorCargoUseCase;
        this.atualizarFotoUsuarioUseCase = atualizarFotoUsuarioUseCase;
        this.filesEntityRepository = filesEntityRepository;
    }

    @PostMapping
    @Operation(summary = "Cadastrar cliente")
    public ResponseEntity<UsuarioListagemCommand> cadastrar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do cliente a ser cadastrado",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ClienteCadastroDto.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de cliente válido que retorna o código 201",
                                            summary = "Exemplo de cliente válido",
                                            value = "{ \"nome\": \"Gisele\", \"email\": \"gisele@gmail.com\", \"telefone\": \"11999999999\", \"senha\": \"BeiraMar123\", \"dtNasc\": \"2000-01-01\" , \"fkCargo\": \"1\"}"
                                    ),
                                    @ExampleObject(
                                            name = "Exemplo de cliente inválido que retorna o código 400",
                                            summary = "Exemplo de cliente inválido",
                                            value = "{\"nome\": \"Gisele\", \"email\": \"gisele\", \"telefone\": \"11777777777\", \"cpf\": \"12345678888\", \"senha\": \"Beira\", \"dtNasc\": \"2000-01-01\" }"
                                    ),
                                    @ExampleObject(
                                            name = "Exemplo de recuperação de senha",
                                            summary = "Exemplo de recuperação de senha",
                                            value = "{\"nome\": \"Vitor\", \"email\": \"vitorhideo.mf@gmail.com\", \"telefone\": \"11888888888\", \"senha\": \"BeiraMar2025\", \"dtNasc\": \"2000-01-01\", \"fkCargo\": \"1\",}"
                                    )
                            })) @RequestBody @Valid UsuarioCadastroCommand command) {

        Usuario usuario = cadastrarUsuarioUseCase.executar(command);
        return ResponseEntity.status(201).body(toListagemCommand(usuario));
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    public ResponseEntity<List<UsuarioListagemCommand>> listar() {
        List<Usuario> clientes = listarUsuariosPorCargoUseCase.executar("CLIENTE");
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes.stream().map(this::toListagemCommand).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ResponseEntity<UsuarioListagemCommand> buscarPorId(@PathVariable Integer id) {
        Usuario usuario = buscarUsuarioPorIdUseCase.executar(id);
        return ResponseEntity.ok(toListagemCommand(usuario));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente")
    public ResponseEntity<UsuarioListagemCommand> atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid UsuarioAtualizacaoCommand command
    ) {
        Usuario atualizado = atualizarUsuarioUseCase.executar(id, command);
        return ResponseEntity.ok(toListagemCommand(atualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar cliente")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/foto")
    @Operation(summary = "Atualizar foto do cliente")
    public ResponseEntity<UsuarioListagemCommand> atualizarFoto(
            @PathVariable Integer id,
            @RequestParam Integer fotoId) {

        Usuario usuario = atualizarFotoUsuarioUseCase.executar(id, fotoId);

        // Monta a URL da foto
        String fotoUrl = null;
        if (usuario.getFotoPerfilId() != null) {
            fotoUrl = "https://meu-bucket.s3.amazonaws.com/" +
                    filesEntityRepository.findById(usuario.getFotoPerfilId())
                            .map(FilesEntity::getStoredName)
                            .orElse(null);
        }

        UsuarioListagemCommand response = new UsuarioListagemCommand(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getDtNasc(),
                usuario.getCargo().getNome(),
                fotoUrl
        );

        return ResponseEntity.ok(response);
    }

    private UsuarioListagemCommand toListagemCommand(Usuario usuario) {
        String fotoUrl = null;

        if (usuario.getFotoPerfilId() != null) {
            fotoUrl = "https://meu-bucket.s3.amazonaws.com/" +
                    filesEntityRepository.findById(usuario.getFotoPerfilId())
                            .map(FilesEntity::getStoredName)
                            .orElse(null);
        }

        return new UsuarioListagemCommand(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getDtNasc(),
                usuario.getCargo().getNome(),
                fotoUrl
        );
    }

}
