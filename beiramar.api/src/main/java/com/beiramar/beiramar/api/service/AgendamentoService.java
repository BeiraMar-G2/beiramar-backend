package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoAtualizacaoDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoCadastroDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoListagemDto;
import com.beiramar.beiramar.api.dto.mapper.AgendamentoMapper;
import com.beiramar.beiramar.api.entity.Agendamento;
import com.beiramar.beiramar.api.entity.Pacote;
import com.beiramar.beiramar.api.entity.Servico;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.AgendamentoRepository;
import com.beiramar.beiramar.api.repository.PacoteRepository;
import com.beiramar.beiramar.api.repository.ServicoRepository;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicoRepository servicoRepository;
    private final PacoteRepository pacoteRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, UsuarioRepository usuarioRepository, ServicoRepository servicoRepository, PacoteRepository pacoteRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicoRepository = servicoRepository;
        this.pacoteRepository = pacoteRepository;
    }

    public AgendamentoListagemDto cadastrar(AgendamentoCadastroDto dto) {
        Usuario cliente = usuarioRepository.findById(dto.getFkCliente())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        Usuario funcionario = usuarioRepository.findById(dto.getFkFuncionario())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado"));
        Servico servico = servicoRepository.findById(dto.getFkServico())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço não encontrado"));

        Pacote pacote = null;
        if (dto.getFkPacote() != null) {
            Optional<Pacote> pacoteOptional = pacoteRepository.findById(dto.getFkPacote());
            if (pacoteOptional.isPresent()) {
                pacote = pacoteOptional.get();
            }
        }

        Agendamento agendamento = AgendamentoMapper.toEntity(dto, cliente, funcionario, servico, pacote);
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
        Agendamento agendamento = agendamentoRepository.findById(id)
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
}
