package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.mapper.ValorPacoteMapper;
import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteCadastroDto;
import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteListagemDto;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.valorpacotepersistence.ValorPacoteEntity;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.valorpacotepersistence.ValorPacoteJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValorPacoteService {

    private final ValorPacoteJpaRepository valorPacoteRepository;
    private final UsuarioJpaRepository usuarioRepository;
    private final PacoteJpaRepository pacoteRepository;

    public ValorPacoteService(ValorPacoteJpaRepository valorPacoteRepository, UsuarioJpaRepository usuarioRepository, PacoteJpaRepository pacoteRepository) {
        this.valorPacoteRepository = valorPacoteRepository;
        this.usuarioRepository = usuarioRepository;
        this.pacoteRepository = pacoteRepository;
    }

    public ValorPacoteListagemDto cadatrar(ValorPacoteCadastroDto dto){

        PacoteEntity pacote = pacoteRepository.findById(dto.getFkPacote())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pacote não encontrado"));

        UsuarioEntity usuario = usuarioRepository.findById(dto.getFkUsuario())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));

        ValorPacoteEntity valorPacote = ValorPacoteMapper.toEntity(dto, usuario, pacote);
        return ValorPacoteMapper.toDto(valorPacoteRepository.save(valorPacote));
    }

    public List<ValorPacoteListagemDto> listarTodos() {
        return valorPacoteRepository.findAll().stream()
                .map(ValorPacoteMapper::toDto)
                .collect(Collectors.toList());
    }

    public ValorPacoteListagemDto buscarPorId(Integer id) {
        return valorPacoteRepository.findById(id)
                .map(ValorPacoteMapper::toDto)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Valor Pacote não encontrado"));
    }

    public ValorPacoteListagemDto atualizar(Integer id, ValorPacoteAtualizacaoDto dto) {
        ValorPacoteEntity valorPacote = valorPacoteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Valor Pacote não encontrado"));
        ValorPacoteMapper.AtualizarEntity(valorPacote, dto);
        return ValorPacoteMapper.toDto(valorPacoteRepository.save(valorPacote));
    }

    public void deletar(Integer id) {
        if (!valorPacoteRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Valor Pacote não encontrado");
        }
        valorPacoteRepository.deleteById(id);
    }
}
