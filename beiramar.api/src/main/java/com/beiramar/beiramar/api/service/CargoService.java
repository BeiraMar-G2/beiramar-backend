package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.*;
import com.beiramar.beiramar.api.dto.mapper.CargoMapper;
import com.beiramar.beiramar.api.dto.mapper.ClienteMapper;
import com.beiramar.beiramar.api.dto.mapper.ServicoMapper;
import com.beiramar.beiramar.api.entity.Cargo;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public CargoListagemDto cadastrarCargo(CargoCadastroDto dto){
        Cargo cargo = CargoMapper.toEntity(dto);
        Cargo salvo = cargoRepository.save(cargo);
        return CargoMapper.toDto(salvo);
    }

    public List<CargoListagemDto> listarTodosCargos(){
        List<Cargo> cargos = cargoRepository.findAll();

        if (cargos.isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhum Cargo Encontrado");
        }

        return cargos.stream()
                .map(CargoMapper::toDto)
                .collect(Collectors.toList());
    }

    public CargoListagemDto buscarCargoPorId(Integer id){
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo não Encontrado"));
        return CargoMapper.toDto(cargo);
    }

    public CargoListagemDto atualizarCargo(Integer id, CargoAtualizacaoDto dto) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo não encontrado"));

        CargoMapper.atualizarEntity(cargo, dto);
        Cargo atualizado = cargoRepository.save(cargo);
        return CargoMapper.toDto(atualizado);
    }


    public void deletarCargo(Integer id){
        if (!cargoRepository.existsById(id)){
            throw new EntidadeNaoEncontradaException("Cargo não Encontrado");
        }
        cargoRepository.deleteById(id);
    }
}
