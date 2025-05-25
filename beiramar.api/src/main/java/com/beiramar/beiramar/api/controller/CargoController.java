package com.beiramar.beiramar.api.controller;

import com.beiramar.beiramar.api.dto.cargoDtos.CargoAtualizacaoDto;
import com.beiramar.beiramar.api.dto.cargoDtos.CargoCadastroDto;
import com.beiramar.beiramar.api.dto.cargoDtos.CargoListagemDto;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.service.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargos")
@Tag(name = "Cargos", description = "Endpoints relacionados a cargos")
public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @Operation(summary = "Cadastro de Cargo")
    @PostMapping
    public ResponseEntity<CargoListagemDto> cadastro(@RequestBody @Valid CargoCadastroDto dto){

        CargoListagemDto cargoCadastro = cargoService.cadastrarCargo(dto);
        return ResponseEntity.status(200).body(cargoCadastro);
    }

    @Operation(summary = "Listagem de Cargo")
    @GetMapping
    public ResponseEntity<List<CargoListagemDto>> listar(){
        List<CargoListagemDto> cargos = cargoService.listarTodosCargos();

        if (cargos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(cargos);
    }

    @Operation(summary = "Atualizar Cargo")
    @PutMapping("/{id}")
    public ResponseEntity<CargoListagemDto> atualizar(@PathVariable Integer id, @RequestBody @Valid CargoAtualizacaoDto dto) {
        return ResponseEntity.ok(cargoService.atualizarCargo(id, dto));
    }

    @Operation(summary = "Deletar Cargo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        try{
            cargoService.deletarCargo(id);
            return ResponseEntity.status(204).build();
        }
        catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(404).build();
        }
    }
}
