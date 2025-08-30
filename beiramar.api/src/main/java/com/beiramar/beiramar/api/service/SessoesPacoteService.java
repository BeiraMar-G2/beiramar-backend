package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.mapper.SessoesPacoteMapper;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteCadastroDto;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteListagemDto;
import com.beiramar.beiramar.api.entity.Pacote;
import com.beiramar.beiramar.api.entity.Servico;
import com.beiramar.beiramar.api.entity.SessoesPacote;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.PacoteRepository;
import com.beiramar.beiramar.api.repository.ServicoRepository;
import com.beiramar.beiramar.api.repository.SessoesPacoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessoesPacoteService {

    private final SessoesPacoteRepository sessoesPacoteRepository;
    private final PacoteRepository pacoteRepository;
    private final ServicoRepository servicoRepository;

    public SessoesPacoteService(SessoesPacoteRepository sessoesPacoteRepository, PacoteRepository pacoteRepository, ServicoRepository servicoRepository) {
        this.sessoesPacoteRepository = sessoesPacoteRepository;
        this.pacoteRepository = pacoteRepository;
        this.servicoRepository = servicoRepository;
    }

    public SessoesPacoteListagemDto cadastrar(SessoesPacoteCadastroDto dto){

        Servico servico = servicoRepository.findById(dto.getFkServico())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço não encontrado"));

        Pacote pacote = pacoteRepository.findById(dto.getFkPacote())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pacote não encontrado"));

        SessoesPacote sessoesPacote = SessoesPacoteMapper.toEntity(dto, pacote, servico);
        return SessoesPacoteMapper.toDto(sessoesPacoteRepository.save(sessoesPacote));
    }

    public List<SessoesPacoteListagemDto> listarTodos() {
        return sessoesPacoteRepository.findAll().stream()
                .map(SessoesPacoteMapper::toDto)
                .collect(Collectors.toList());
    }

    public SessoesPacoteListagemDto buscarPorId(Integer id) {
        return sessoesPacoteRepository.findById(id)
                .map(SessoesPacoteMapper::toDto)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Sessão Pacote não encontrado"));
    }

    public SessoesPacoteListagemDto atualizar(Integer id, SessoesPacoteAtualizacaoDto dto) {
        SessoesPacote sessoesPacote = sessoesPacoteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Sessão Pacote não encontrado"));
        SessoesPacoteMapper.AtualizarEntity(sessoesPacote, dto);
        return SessoesPacoteMapper.toDto(sessoesPacoteRepository.save(sessoesPacote));
    }


    public void deletar(Integer id) {
        if (!sessoesPacoteRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Sessão pacote não encontrado");
        }
        sessoesPacoteRepository.deleteById(id);
    }
}
