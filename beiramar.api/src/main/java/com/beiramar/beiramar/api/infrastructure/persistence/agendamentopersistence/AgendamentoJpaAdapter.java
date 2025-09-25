package com.beiramar.beiramar.api.infrastructure.persistence.agendamentopersistence;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.*;
import com.beiramar.beiramar.api.entity.StatusAgendamento;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AgendamentoJpaAdapter implements AgendamentoGateway {

    private final AgendamentoJpaRepository agendamentoRepository;
    private final UsuarioJpaRepository usuarioRepository;
    private final ServicoJpaRepository servicoRepository;
    private final PacoteJpaRepository pacoteRepository;

    public AgendamentoJpaAdapter(AgendamentoJpaRepository agendamentoRepository,
                                 UsuarioJpaRepository usuarioRepository,
                                 ServicoJpaRepository servicoRepository,
                                 PacoteJpaRepository pacoteRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicoRepository = servicoRepository;
        this.pacoteRepository = pacoteRepository;
    }


    private Agendamento toDomain(AgendamentoEntity entity) {
        Usuario cliente = entity.getCliente() != null ? toDomain(entity.getCliente()) : null;
        Usuario funcionario = entity.getFuncionario() != null ? toDomain(entity.getFuncionario()) : null;
        Servico servico = entity.getServico() != null ? toDomain(entity.getServico()) : null;
        Pacote pacote = entity.getPacote() != null ? toDomain(entity.getPacote()) : null;

        return new Agendamento(
                entity.getIdAgendamento(),
                entity.getDtHora(),
                entity.getValorPago(),
                entity.getStatus(),
                entity.getStatusAgendamento() != null ? entity.getStatusAgendamento().name() : null,
                cliente,
                funcionario,
                servico,
                pacote
        );
    }

    private AgendamentoEntity toEntity(Agendamento domain) {
        AgendamentoEntity entity = new AgendamentoEntity();
        entity.setIdAgendamento(domain.getIdAgendamento());
        entity.setDtHora(domain.getDtHora());
        entity.setValorPago(domain.getValorPago());
        entity.setStatus(domain.getStatus());

        if (domain.getStatusAgendamento() != null) {
            entity.setStatusAgendamento(Enum.valueOf(StatusAgendamento.class, domain.getStatusAgendamento()));
        }
        if (domain.getCliente() != null) {
            entity.setCliente(usuarioRepository.findById(domain.getCliente().getIdUsuario())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado")));
        }
        if (domain.getFuncionario() != null) {
            entity.setFuncionario(usuarioRepository.findById(domain.getFuncionario().getIdUsuario())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado")));
        }
        if (domain.getServico() != null) {
            entity.setServico(servicoRepository.findById(domain.getServico().getIdServico())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço não encontrado")));
        }
        if (domain.getPacote() != null) {
            entity.setPacote(pacoteRepository.findById(domain.getPacote().getIdPacote())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Pacote não encontrado")));
        }
        return entity;
    }

    private Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getIdUsuario(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getSenha(),
                entity.getDtNasc(),
                entity.getCargo() != null ? new com.beiramar.beiramar.api.core.domain.Cargo(entity.getCargo().getIdCargo(), entity.getCargo().getNome()) : null,
                null
        );
    }

    private Servico toDomain(ServicoEntity entity) {
        return new Servico(
                entity.getIdServico(),
                entity.getNome(),
                entity.getPreco(),
                entity.getDescricao(),
                entity.getDuracao()
        );
    }

    private Pacote toDomain(PacoteEntity entity) {
        return new Pacote(
                entity.getIdPacote(),
                entity.getNome(),
                entity.getPrecoTotalSemDesconto(),
                entity.getQtdSessoesTotal(),
                entity.getTempoLimiteDias()
        );
    }

    @Override
    public Agendamento salvar(Agendamento agendamento) {
        AgendamentoEntity entity = toEntity(agendamento);
        AgendamentoEntity salvo = agendamentoRepository.save(entity);
        return toDomain(salvo);
    }

    @Override
    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Agendamento> buscarPorId(Integer id) {
        return agendamentoRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void deletar(Integer id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Agendamento não encontrado com id: " + id);
        }
        agendamentoRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Integer id) {
        return agendamentoRepository.existsById(id);
    }

    @Override
    public Pacote buscarPacotePorId(Integer id) {
        PacoteEntity entity = pacoteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pacote não encontrado com id: " + id));
        return toDomain(entity);
    }

    @Override
    public Servico buscarServicoPorId(Integer id) {
        ServicoEntity entity = servicoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Serviço não encontrado com id: " + id));
        return toDomain(entity);
    }

    @Override
    public Usuario buscarClientePorId(Integer id) {
        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado com id: " + id));
        return toDomain(entity);
    }

    @Override
    public Usuario buscarFuncionarioPorId(Integer id) {
        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado com id: " + id));
        return toDomain(entity);
    }

    @Override
    public List<Agendamento> listarPorIdCliente(Integer idCliente) {
        return agendamentoRepository.findByClienteIdUsuario(idCliente)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Agendamento> listarPorMes(Integer mes, Integer ano) {
        return agendamentoRepository.findByMes(mes, ano)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Long contarAgendamentosComStatusAgendadoPorDias(Integer dias) {
        return agendamentoRepository.countByStatusAgendado(dias);
    }

    @Override
    public Long contarAgendamentosCanceladosPorDias(Integer dias) {
        return agendamentoRepository.countByStatusCancelado(dias);
    }

}
