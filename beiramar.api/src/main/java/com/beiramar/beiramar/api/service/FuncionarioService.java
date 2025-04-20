package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.FuncionarioListagemDto;
import com.beiramar.beiramar.api.dto.mapper.FuncionarioMapper;
import com.beiramar.beiramar.api.entity.Cargo;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.CargoRepository;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final UsuarioRepository usuarioRepository;
    private final CargoRepository cargoRepository;

    public FuncionarioService(UsuarioRepository usuarioRepository, CargoRepository cargoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cargoRepository = cargoRepository;
    }

    public FuncionarioListagemDto cadastrarFuncionario(FuncionarioCadastroDto dto) {

        Cargo cargoFuncionario = cargoRepository.findByNomeIgnoreCase("FUNCIONARIO")
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo FUNCIONARIO não encontrado"));

        Usuario funcionario = FuncionarioMapper.toEntity(dto, cargoFuncionario);
        Usuario salvo = usuarioRepository.save(funcionario);

        return FuncionarioMapper.toDto(salvo);
    }

    public List<FuncionarioListagemDto> listarTodosFuncionarios() {
        Cargo cargoFuncionario = cargoRepository.findByNomeIgnoreCase("FUNCIONARIO")
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo FUNCIONARIO não encontrado"));

        return usuarioRepository.findByCargo(cargoFuncionario).stream()
                .map(FuncionarioMapper::toDto)
                .collect(Collectors.toList());
    }
}
