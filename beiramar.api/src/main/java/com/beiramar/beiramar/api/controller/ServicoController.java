package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.entity.Servico;
import com.beiramar.beiramar.api.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository repository;

    @PostMapping
    public ResponseEntity<Servico> cadastrar (
            @RequestBody Servico servicoParaRegistrar
    ){
        if (servicoParaRegistrar.getNome().isBlank() || servicoParaRegistrar.getPreco().isNaN()) {
            return ResponseEntity.status(400).build();
        }

        Servico servicoRegistrado = repository.save(servicoParaRegistrar);
        return ResponseEntity.status(201).body(servicoRegistrado);
    }

    @GetMapping
    public ResponseEntity<List<Servico>> buscarTodos() {

        List<Servico> servicos = repository.findAll();

        if (servicos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(servicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(
            @PathVariable Integer id
    ){
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Integer id
    ){
        if (repository.findById(id).isEmpty()){
            return ResponseEntity.status(404).build();
        }
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(
            @PathVariable Integer id,
            @RequestBody Servico servicoAtualizado
    ) {
        if (repository.findById(id).isEmpty()){
            return ResponseEntity.status(404).build();
        }

        servicoAtualizado.setId(id);
        repository.save(servicoAtualizado);
        return ResponseEntity.status(200).body(servicoAtualizado);
    }
}
