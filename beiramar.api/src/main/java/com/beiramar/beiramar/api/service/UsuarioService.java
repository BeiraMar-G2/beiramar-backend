package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.FuncionarioListagemDto;
import com.beiramar.beiramar.api.dto.mapper.FuncionarioMapper;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public FuncionarioListagemDto cadastrar(FuncionarioCadastroDto request) {
        Usuario funcionarioCadastrado = FuncionarioMapper.toUsuario(request);
        funcionarioCadastrado = usuarioRepository.save(funcionarioCadastrado);

        FuncionarioListagemDto response = FuncionarioMapper.toFuncionarioListagemDto(funcionarioCadastrado);

        return response;
    }

    public List<FuncionarioListagemDto> listarTodos() {
        List<FuncionarioListagemDto> response = usuarioRepository.findAll().stream()
                .map(FuncionarioMapper::toFuncionarioListagemDto)
                .toList();

        return response;
    }
}
