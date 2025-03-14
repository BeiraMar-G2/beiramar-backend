package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.entity.Cliente;
import com.beiramar.beiramar.api.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public ResponseEntity<Cliente> criar(
            @Valid @RequestBody Cliente clienteParaRegistrar) {

        Boolean existePorEmail = repository.existsByEmail(clienteParaRegistrar.getEmail());
        Boolean existePorTelefone = repository.existsByTelefone(clienteParaRegistrar.getTelefone());

        if (existePorEmail || existePorTelefone) {
            return ResponseEntity.status(409).build();
        }
        clienteParaRegistrar = repository.save(clienteParaRegistrar);
        return ResponseEntity.status(201).body(clienteParaRegistrar);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos() {

        List<Cliente> clientes = repository.findAll();

        if (clientes.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(
            @PathVariable Integer id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Integer id) {
        if (repository.findById(id).isEmpty()){
            return ResponseEntity.status(404).build();
        }
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(
            @Valid
            @PathVariable Integer id,
            @RequestBody Cliente clienteAtualizado
    ) {
        if (repository.findById(id).isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Cliente existePorEmail = repository.findByEmail(clienteAtualizado.getEmail());
        Cliente existePorTelefone = repository.findByTelefone(clienteAtualizado.getTelefone());

        if (existePorEmail != null && !existePorEmail.getId().equals(id)) {
            return ResponseEntity.status(409).build();
        }

        if (existePorTelefone != null && !existePorTelefone.getId().equals(id)) {
            return ResponseEntity.status(409).build();
        }

        clienteAtualizado.setId(id);
        repository.save(clienteAtualizado);
        return ResponseEntity.status(200).body(clienteAtualizado);
    }
}
