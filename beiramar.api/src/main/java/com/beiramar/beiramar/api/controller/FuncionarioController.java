package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.entity.Funcionario;
import com.beiramar.beiramar.api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {


    @Autowired
    private FuncionarioRepository repository;

    @PostMapping
    public ResponseEntity<Funcionario> criar(
            @RequestBody Funcionario funcionarioParaRegistrar) {

        Boolean existePorEmail = repository.existsByEmail(funcionarioParaRegistrar.getEmail());
        Boolean existePorCpf = repository.existsByCpf(funcionarioParaRegistrar.getCpf());

        if (existePorEmail || existePorCpf) {
            return ResponseEntity.status(409).build();
        }
        funcionarioParaRegistrar = repository.save(funcionarioParaRegistrar);
        return ResponseEntity.status(201).body(funcionarioParaRegistrar);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> buscarTodos() {

        List<Funcionario> funcionarios = repository.findAll();

        if (funcionarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(
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
    public ResponseEntity<Funcionario> atualizar(
            @PathVariable Integer id,
            @RequestBody Funcionario funcionarioAtualizado
    ) {
        if (repository.findById(id).isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Funcionario existePorEmail = repository.findByEmail(funcionarioAtualizado.getEmail());
        Funcionario existePorCpf = repository.findByCpf(funcionarioAtualizado.getCpf());

        if (existePorEmail != null && !existePorEmail.getId().equals(id)) {
            return ResponseEntity.status(409).build();
        }

        if (existePorCpf != null && !existePorCpf.getId().equals(id)) {
            return ResponseEntity.status(409).build();
        }

        funcionarioAtualizado.setId(id);
        repository.save(funcionarioAtualizado);
        return ResponseEntity.status(200).body(funcionarioAtualizado);
    }
}
