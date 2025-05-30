package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.disponibilidadeDtos.DisponibilidadeAtualizacaoDto;
import com.beiramar.beiramar.api.dto.disponibilidadeDtos.DisponibilidadeCadastroDto;
import com.beiramar.beiramar.api.dto.disponibilidadeDtos.DisponibilidadeListagemDto;
import com.beiramar.beiramar.api.dto.mapper.DisponibilidadeMapper;
import com.beiramar.beiramar.api.entity.Disponibilidade;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.DisponibilidadeRepository;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisponibilidadeService {

    private final DisponibilidadeRepository disponibilidadeRepository;
    private final UsuarioRepository usuarioRepository;

    public DisponibilidadeService(DisponibilidadeRepository disponibilidadeRepository, UsuarioRepository usuarioRepository) {
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public DisponibilidadeListagemDto cadastrar(DisponibilidadeCadastroDto dto){

        Usuario funcionario = usuarioRepository.findById(dto.getIdFuncionario())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado"));

        Disponibilidade disponibilidade = DisponibilidadeMapper.toEntity(dto, funcionario);
        return DisponibilidadeMapper.toDto(disponibilidadeRepository.save(disponibilidade));
    }

    public List<DisponibilidadeListagemDto> listarTodos() {
        return disponibilidadeRepository.findAll().stream()
                .map(DisponibilidadeMapper::toDto)
                .collect(Collectors.toList());
    }

    public DisponibilidadeListagemDto buscarPorId(Integer id) {
        return disponibilidadeRepository.findById(id)
                .map(DisponibilidadeMapper::toDto)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Disponibilidade não encontrado"));
    }

    public DisponibilidadeListagemDto atualizar(Integer id, DisponibilidadeAtualizacaoDto dto) {
        Disponibilidade disponibilidade = disponibilidadeRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Disponibilidade não encontrado"));
        DisponibilidadeMapper.AtualizarEntity(disponibilidade, dto);
        return DisponibilidadeMapper.toDto(disponibilidadeRepository.save(disponibilidade));
    }

    public void deletar(Integer id) {
        if (!disponibilidadeRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Disponibilidade não encontrado");
        }
        disponibilidadeRepository.deleteById(id);
    }
}
