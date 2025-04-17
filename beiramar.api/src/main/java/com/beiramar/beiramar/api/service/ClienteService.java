package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.ClienteCadastroDto;
import com.beiramar.beiramar.api.dto.ClienteListagemDto;
import com.beiramar.beiramar.api.dto.mapper.ClienteMapper;
import com.beiramar.beiramar.api.entity.TipoUsuarioEnum;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final UsuarioRepository usuarioRepository;

    public ClienteService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ClienteListagemDto cadastrar(ClienteCadastroDto dto) {
        Usuario cliente = ClienteMapper.toClienteDto(dto);
        cliente.setTipoUsuario(TipoUsuarioEnum.CLIENTE);
        Usuario salvo = usuarioRepository.save(cliente);
        return ClienteMapper.toClienteListagemDto(salvo);
    }

    public List<ClienteListagemDto> listarTodos() {
        return usuarioRepository.findAllByTipoUsuario(TipoUsuarioEnum.CLIENTE)
                .stream()
                .map(ClienteMapper::toClienteListagemDto)
                .collect(Collectors.toList());
    }
}
