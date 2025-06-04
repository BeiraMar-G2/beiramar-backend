package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.clienteDtos.ClienteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.clienteDtos.ClienteCadastroDto;
import com.beiramar.beiramar.api.dto.clienteDtos.ClienteListagemDto;
import com.beiramar.beiramar.api.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Endpoints relacionados a clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Cadastro de clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado", content = @Content(
                    schema = @Schema(implementation = ClienteListagemDto.class),
                    examples = {
                            @ExampleObject(
                                value = "{\"id\": \"1\", \"nome\": \"Gisele\", \"email\": \"gisele@gmail.com\", \"telefone\": \"11999999999\"}"
                            )
                    })),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content())
    })
    @PostMapping
    public ResponseEntity<ClienteListagemDto> cadastrar(
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
    })) @RequestBody @Valid ClienteCadastroDto dto) {
        ClienteListagemDto clienteCadastrado = clienteService.cadastrarCliente(dto);
        return ResponseEntity.status(201).body(clienteCadastrado);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Clientes encontrados", content = @Content(
                    schema = @Schema(implementation = ClienteListagemDto.class),
                    examples = {
                            @ExampleObject(
                                    value = "{\"id\": \"1\", \"nome\": \"Gisele\", \"email\": \"gisele@gmail.com\", \"telefone\": \"11999999999\"}"
                            )
                    })),
            @ApiResponse(responseCode = "204", description = "Nenhum cliente cadastrado ainda", content = @Content())
    })
    @Operation(summary = "Listagem de clientes")
    @GetMapping
    public ResponseEntity<List<ClienteListagemDto>> listar() {
        List<ClienteListagemDto> clientes = clienteService.listarTodosClientes();

        if (clientes.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(clientes);
    }

    @Operation(summary = "Buscar cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteListagemDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @Operation(summary = "Atualizar cliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteListagemDto> atualizar(@PathVariable Integer id, @RequestBody @Valid ClienteAtualizacaoDto dto) {
        return ResponseEntity.ok(clienteService.atualizarCliente(id, dto));
    }

    @Operation(summary = "Deletar cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
