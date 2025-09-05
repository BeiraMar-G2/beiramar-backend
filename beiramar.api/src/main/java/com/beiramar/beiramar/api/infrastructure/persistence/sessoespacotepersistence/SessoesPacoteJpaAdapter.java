package com.beiramar.beiramar.api.infrastructure.persistence.sessoespacotepersistence;

import com.beiramar.beiramar.api.core.adapter.SessoesPacoteGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Pacote;
import com.beiramar.beiramar.api.core.domain.Servico;
import com.beiramar.beiramar.api.core.domain.SessoesPacote;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SessoesPacoteJpaAdapter implements SessoesPacoteGateway {

    private SessoesPacoteJpaRepository sessoesPacoteJpaRepository;
    private PacoteJpaRepository pacoteJpaRepository;
    private ServicoJpaRepository servicoJpaRepository;

    public SessoesPacoteJpaAdapter(SessoesPacoteJpaRepository sessoesPacoteJpaRepository, PacoteJpaRepository pacoteJpaRepository, ServicoJpaRepository servicoJpaRepository) {
        this.sessoesPacoteJpaRepository = sessoesPacoteJpaRepository;
        this.pacoteJpaRepository = pacoteJpaRepository;
        this.servicoJpaRepository = servicoJpaRepository;
    }

    private SessoesPacote toDomain(SessoesPacoteEntity entity){
        Pacote pacote = new Pacote(
                entity.getPacote().getIdPacote(),
                entity.getPacote().getNome(),
                entity.getPacote().getPrecoTotalSemDesconto(),
                entity.getPacote().getQtdSessoesTotal(),
                entity.getPacote().getTempoLimiteDias()
        );

        Servico servico = new Servico(
                entity.getServico().getIdServico(),
                entity.getServico().getNome(),
                entity.getServico().getPreco(),
                entity.getServico().getDescricao(),
                entity.getServico().getDuracao()
        );

        return new SessoesPacote(
                entity.getIdSessoesPacote(),
                entity.getQtdSessoes(),
                pacote,
                servico
        );
    }

    private SessoesPacoteEntity toEntity(SessoesPacote sessoesPacote){

        PacoteEntity pacoteEntity = pacoteJpaRepository.findById(sessoesPacote.getPacote().getIdPacote())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pacote não encontrado"));

        ServicoEntity servicoEntity = servicoJpaRepository.findById(sessoesPacote.getServico().getIdServico())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Servico não encontrado"));

        SessoesPacoteEntity entity = new SessoesPacoteEntity();
        entity.setIdSessoesPacote(sessoesPacote.getIdSessoesPacote());
        entity.setQtdSessoes(sessoesPacote.getQtdSessoes());
        entity.setPacote(pacoteEntity);
        entity.setServico(servicoEntity);

        return entity;
    }

    @Override
    public SessoesPacote salvar(SessoesPacote sessoesPacote) {
        SessoesPacoteEntity salvo = sessoesPacoteJpaRepository.save(toEntity(sessoesPacote));
        return toDomain(salvo);
    }

    @Override
    public List<SessoesPacote> listarTodos() {
        return sessoesPacoteJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SessoesPacote> buscarPorId(Integer id) {
        return sessoesPacoteJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void deletar(Integer id) {
        sessoesPacoteJpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Integer id) {
        return sessoesPacoteJpaRepository.existsById(id);
    }

    @Override
    public Pacote buscarPacotePorId(Integer id) {
        return pacoteJpaRepository.findById(id)
                .map(entity -> new Pacote(
                        entity.getIdPacote(),
                        entity.getNome(),
                        entity.getPrecoTotalSemDesconto(),
                        entity.getQtdSessoesTotal(),
                        entity.getTempoLimiteDias()
                ))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pacote não encontrado com ID: " + id));
    }

    @Override
    public Servico buscarServicoPorId(Integer id) {
        return servicoJpaRepository.findById(id)
                .map(entity -> new Servico(
                        entity.getIdServico(),
                        entity.getNome(),
                        entity.getPreco(),
                        entity.getDescricao(),
                        entity.getDuracao()
                ))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Servico não encontrado com ID: " + id));
    }

    @Override
    public List<Servico> buscarServicosPorPacote(Integer idPacote) {
        return sessoesPacoteJpaRepository.buscarServicosPorPacote(idPacote)
                .stream().map(servicoEntity -> new Servico(
                        servicoEntity.getIdServico(),
                        servicoEntity.getNome(),
                        servicoEntity.getPreco(),
                        servicoEntity.getDescricao(),
                        servicoEntity.getDuracao()
                )).toList();

    }

}

