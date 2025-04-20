package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.ClienteCadastroDto;
import com.beiramar.beiramar.api.dto.ClienteListagemDto;
import com.beiramar.beiramar.api.dto.mapper.ClienteMapper;
import com.beiramar.beiramar.api.entity.Cargo;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.CargoRepository;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final UsuarioRepository usuarioRepository;

    private final CargoRepository cargoRepository;

    public ClienteService(UsuarioRepository usuarioRepository, CargoRepository cargoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cargoRepository = cargoRepository;
    }

    public ClienteListagemDto cadastrarCliente(ClienteCadastroDto dto) {

        Cargo cargoCliente = cargoRepository.findByNomeIgnoreCase("CLIENTE")
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo CLIENTE não encontrado"));

        Usuario cliente = ClienteMapper.toEntity(dto, cargoCliente);
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
}
