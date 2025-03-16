package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.entity.Agendamento;
import com.beiramar.beiramar.api.repository.AgendamentoRepository;
import com.beiramar.beiramar.api.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private ServicoRepository servicoRepository;

    @PostMapping
    public ResponseEntity<Agendamento> criar(
            @RequestBody Agendamento agendamentoParaRegistrar){

        if (agendamentoParaRegistrar.getDtHora() == null || agendamentoParaRegistrar.getStatus().isBlank()){
            return ResponseEntity.status(400).build();
        }

        if (!servicoRepository.existsById(agendamentoParaRegistrar.getServico().getId())){
            return ResponseEntity.status(400).build();
        }
        
        Agendamento agendamentoRegistrado = repository.save(agendamentoParaRegistrar);
        return ResponseEntity.status(201).body(agendamentoRegistrado);

    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> buscarTodos(){

        List<Agendamento> agendamentos = repository.findAll();

        if (agendamentos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(agendamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(
            @PathVariable Integer id){
        return ResponseEntity.of(repository.findById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Agendamento>> buscarPorStatus(
            @PathVariable String status){

        List<Agendamento> agendamentos = repository.findByStatus(status);

        if (agendamentos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(agendamentos);
    }

    @GetMapping("/data-depois")
    public ResponseEntity<List<Agendamento>> buscarPorDataDepois(
            @RequestParam LocalDateTime data){

        List<Agendamento> agendamentos = repository.findByDtHoraAfter(data);

        if (agendamentos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(agendamentos);
    }

    @GetMapping("/data-antes")
    public ResponseEntity<List<Agendamento>> buscarPorDataAntes(
            @RequestParam LocalDateTime data){

        List<Agendamento> agendamentos = repository.findByDtHoraBefore(data);

        if (agendamentos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(agendamentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Integer id){

        if (repository.findById(id).isEmpty()){
            return ResponseEntity.status(404).build();
        }

        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(
            @RequestBody Agendamento agendamentoParaAtualizar,
            @PathVariable Integer id){

        if (repository.findById(id).isEmpty()){
            return ResponseEntity.status(404).build();
        }

        if (!servicoRepository.existsById(agendamentoParaAtualizar.getServico().getId())){
            return ResponseEntity.status(400).build();
        }

        agendamentoParaAtualizar.setId(id);
        Agendamento agendamentoAtualizado = repository.save(agendamentoParaAtualizar);
        return ResponseEntity.status(200).body(agendamentoAtualizado);

    }
}
