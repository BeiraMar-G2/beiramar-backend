package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.ServicoCadastroDto;
import com.beiramar.beiramar.api.dto.ServicoListagemDto;
import com.beiramar.beiramar.api.dto.mapper.ServicoMapper;
import com.beiramar.beiramar.api.entity.Servico;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {
    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public ServicoListagemDto cadastrarServico(ServicoCadastroDto dto) {
        Servico servico = ServicoMapper.toEntity(dto);
        Servico salvo = servicoRepository.save(servico);
        return ServicoMapper.toDto(salvo);
    }

    public List<ServicoListagemDto> listarTodosServicos() {
        List<Servico> servicos = servicoRepository.findAll();

        if (servicos.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum servico encontrado");
        }

        return servicos.stream()
                .map(ServicoMapper::toDto)
                .collect(Collectors.toList());
    }

    public ServicoListagemDto buscarServicoPorId(Integer id) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço não encontrado"));
        return ServicoMapper.toDto(servico);
    }

    public ServicoListagemDto atualizarServico(Integer id, ServicoCadastroDto dto) {
        Servico servicoExistente = servicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço não encontrado"));

        servicoExistente.setNome(dto.getNome());
        servicoExistente.setPreco(dto.getPreco());
        servicoExistente.setDuracao(dto.getDuracao());

        Servico atualizado = servicoRepository.save(servicoExistente);
        return ServicoMapper.toDto(atualizado);
    }

    public void deletarServico(Integer id) {
        if (!servicoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Serviço não encontrado");
        }
        servicoRepository.deleteById(id);
    }

}

