package com.beiramar.beiramar.api.service;


import com.beiramar.beiramar.api.dto.mapper.PacoteMapper;
import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteCadastroDto;
import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteListagemDto;
import com.beiramar.beiramar.api.entity.Pacote;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.PacoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacoteService {

    private final PacoteRepository pacoteRepository;

    public PacoteService(PacoteRepository pacoteRepository) {
        this.pacoteRepository = pacoteRepository;
    }


    public PacoteListagemDto cadastrar(PacoteCadastroDto dto) {
        Pacote pacote = PacoteMapper.toEntity(dto);
        return PacoteMapper.toDto(pacoteRepository.save(pacote));
    }

    public List<PacoteListagemDto> listarTodos() {
        return pacoteRepository.findAll().stream()
                .map(PacoteMapper::toDto)
                .collect(Collectors.toList());
    }

    public PacoteListagemDto buscarPorId(Integer id) {
        Pacote pacote = pacoteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pacote não encontrado"));
        return PacoteMapper.toDto(pacote);
    }

    public PacoteListagemDto atualizar(Integer id, PacoteAtualizacaoDto dto) {
        Pacote pacote = pacoteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pacote não encontrado"));
        PacoteMapper.AtualizarEntity(pacote, dto);
        return PacoteMapper.toDto(pacoteRepository.save(pacote));
    }

    public void deletar(Integer id) {
        if (!pacoteRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Pacote não encontrado");
        }
        pacoteRepository.deleteById(id);
    }
}
