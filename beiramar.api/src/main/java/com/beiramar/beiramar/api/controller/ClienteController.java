package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.ClienteCadastroDto;
import com.beiramar.beiramar.api.dto.ClienteListagemDto;
import com.beiramar.beiramar.api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteListagemDto> cadastrar(@RequestBody @Valid ClienteCadastroDto dto) {
        ClienteListagemDto clienteCadastrado = clienteService.cadastrar(dto);
        return ResponseEntity.status(201).body(clienteCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<ClienteListagemDto>> listar() {
        List<ClienteListagemDto> clientes = clienteService.listarTodos();

        if (clientes.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(clientes);
    }
}
