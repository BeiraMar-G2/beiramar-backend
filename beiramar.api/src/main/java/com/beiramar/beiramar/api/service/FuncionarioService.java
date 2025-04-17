package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.FuncionarioListagemDto;
import com.beiramar.beiramar.api.dto.mapper.FuncionarioMapper;
import com.beiramar.beiramar.api.entity.TipoUsuarioEnum;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final UsuarioRepository usuarioRepository;

    public FuncionarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public FuncionarioListagemDto cadastrar(FuncionarioCadastroDto dto) {
        Usuario funcionario = FuncionarioMapper.toFuncionarioDto(dto);
        funcionario.setTipoUsuario(TipoUsuarioEnum.FUNCIONARIO);
        Usuario salvo = usuarioRepository.save(funcionario);
        return FuncionarioMapper.toFuncionarioListagemDto(salvo);
    }

    public List<FuncionarioListagemDto> listarTodos() {
        return usuarioRepository.findAllByTipoUsuario(TipoUsuarioEnum.FUNCIONARIO)
                .stream()
                .map(FuncionarioMapper::toFuncionarioListagemDto)
                .collect(Collectors.toList());
    }
}
