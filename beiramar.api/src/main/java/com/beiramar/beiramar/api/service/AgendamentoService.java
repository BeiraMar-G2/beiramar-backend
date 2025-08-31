package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoAtualizacaoDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoCadastroDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoListagemDto;
import com.beiramar.beiramar.api.dto.mapper.AgendamentoMapper;
import com.beiramar.beiramar.api.entity.AgendamentoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.AgendamentoRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final UsuarioJpaRepository usuarioRepository;
    private final ServicoJpaRepository servicoRepository;
    private final PacoteJpaRepository pacoteRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, UsuarioJpaRepository usuarioRepository, ServicoJpaRepository servicoRepository, PacoteJpaRepository pacoteRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicoRepository = servicoRepository;
        this.pacoteRepository = pacoteRepository;
    }

    public AgendamentoListagemDto cadastrar(AgendamentoCadastroDto dto) {
        UsuarioEntity cliente = usuarioRepository.findById(dto.getFkCliente())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        UsuarioEntity funcionario = usuarioRepository.findById(dto.getFkFuncionario())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado"));
        ServicoEntity servico = servicoRepository.findById(dto.getFkServico())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço não encontrado"));

        PacoteEntity pacote = null;
        if (dto.getFkPacote() != null) {
            Optional<PacoteEntity> pacoteOptional = pacoteRepository.findById(dto.getFkPacote());
            if (pacoteOptional.isPresent()) {
                pacote = pacoteOptional.get();
            }
        }

        AgendamentoEntity agendamento = AgendamentoMapper.toEntity(dto, cliente, funcionario, servico, pacote);
        return AgendamentoMapper.toDto(agendamentoRepository.save(agendamento));
    }

    public List<AgendamentoListagemDto> listarTodos() {
        return agendamentoRepository.findAll().stream()
                .map(AgendamentoMapper::toDto)
                .collect(Collectors.toList());
    }

    public AgendamentoListagemDto buscarPorId(Integer id) {
        return agendamentoRepository.findById(id)
                .map(AgendamentoMapper::toDto)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Agendamento não encontrado"));
    }

    public AgendamentoListagemDto atualizar(Integer id, AgendamentoAtualizacaoDto dto) {
        AgendamentoEntity agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Agendamento não encontrado"));
        AgendamentoMapper.AtualizarEntity(agendamento, dto);
        return AgendamentoMapper.toDto(agendamentoRepository.save(agendamento));
    }

    public void deletar(Integer id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Agendamento não encontrado");
        }
        agendamentoRepository.deleteById(id);
    }

    /*
    public List<AgendamentoListagemDto> listarPorMes(int ano, int mes) {
        YearMonth yearMonth = YearMonth.of(ano, mes);
        LocalDateTime start = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime end = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        return agendamentoRepository.findByMes(start, end).stream()
                .map(AgendamentoMapper::toDto)
                .collect(Collectors.toList());
    }

     */
}
