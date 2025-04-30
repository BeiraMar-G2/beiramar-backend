package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.ClienteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.ClienteCadastroDto;
import com.beiramar.beiramar.api.dto.ClienteListagemDto;
import com.beiramar.beiramar.api.dto.mapper.ClienteMapper;
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
public class ClienteService {

    private final UsuarioRepository usuarioRepository;
    private final CargoRepository cargoRepository;
    private final PasswordEncoder passwordEncoder;

    public ClienteService(UsuarioRepository usuarioRepository, CargoRepository cargoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.cargoRepository = cargoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ClienteListagemDto cadastrarCliente(ClienteCadastroDto dto) {

        Cargo cargoCliente = cargoRepository.findByNomeIgnoreCase("CLIENTE")
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo CLIENTE não encontrado"));

        Usuario cliente = ClienteMapper.toEntity(dto, cargoCliente);
        cliente.setSenha(passwordEncoder.encode(dto.getSenha()));

        Usuario salvo = usuarioRepository.save(cliente);

        return ClienteMapper.toDto(salvo);
    }

    public List<ClienteListagemDto> listarTodosClientes() {
        Cargo cargoCliente = cargoRepository.findByNomeIgnoreCase("CLIENTE")
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo CLIENTE não encontrado"));

        return usuarioRepository.findByCargo(cargoCliente).stream()
                .map(ClienteMapper::toDto)
                .collect(Collectors.toList());
    }

    public ClienteListagemDto buscarPorId(Integer id) {
        Usuario cliente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        return ClienteMapper.toDto(cliente);
    }

    public ClienteListagemDto atualizarCliente(Integer id, ClienteAtualizacaoDto dto) {
        Usuario cliente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));

        ClienteMapper.atualizarEntity(cliente, dto);
        Usuario atualizado = usuarioRepository.save(cliente);
        return ClienteMapper.toDto(atualizado);
    }

    public void deletarCliente(Integer id) {
        Usuario cliente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        usuarioRepository.delete(cliente);
    }
}
