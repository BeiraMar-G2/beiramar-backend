package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.funcionarioDtos.FuncionarioAtualizacaoDto;
import com.beiramar.beiramar.api.dto.funcionarioDtos.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.funcionarioDtos.FuncionarioListagemDto;
import com.beiramar.beiramar.api.dto.mapper.FuncionarioMapper;
import com.beiramar.beiramar.api.entity.Cargo;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.CargoRepository;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final UsuarioRepository usuarioRepository;
    private final CargoRepository cargoRepository;
    private final PasswordEncoder passwordEncoder;

    public FuncionarioService(UsuarioRepository usuarioRepository, CargoRepository cargoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.cargoRepository = cargoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public FuncionarioListagemDto cadastrarFuncionario(FuncionarioCadastroDto dto) {

        Cargo cargoFuncionario = cargoRepository.findByNomeIgnoreCase("FUNCIONARIO")
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo FUNCIONARIO não encontrado"));

        Usuario funcionario = FuncionarioMapper.toEntity(dto, cargoFuncionario);
        funcionario.setSenha(passwordEncoder.encode(funcionario.getSenha()));
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

    public FuncionarioListagemDto buscarPorId(Integer id) {
        Usuario funcionario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado"));
        return FuncionarioMapper.toDto(funcionario);
    }

    public FuncionarioListagemDto atualizarFuncionario(Integer id, FuncionarioAtualizacaoDto dto) {
        Usuario funcionario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado"));

        FuncionarioMapper.atualizarEntity(funcionario, dto);
        Usuario atualizado = usuarioRepository.save(funcionario);
        return FuncionarioMapper.toDto(atualizado);
    }

    public void deletarFuncionario(Integer id) {
        Usuario funcionario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado"));
        usuarioRepository.delete(funcionario);
    }
}
