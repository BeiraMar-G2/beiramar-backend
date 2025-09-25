package com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.domain.Servico;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServicoJpaAdapter implements ServicoGateway {

    private final ServicoJpaRepository servicoJpaRepository;

    public ServicoJpaAdapter(ServicoJpaRepository servicoJpaRepository) {
        this.servicoJpaRepository = servicoJpaRepository;
    }

    private Servico toDomain(ServicoEntity entity) {
        return new Servico(entity.getIdServico(), entity.getNome(), entity.getPreco(),
                entity.getDescricao(), entity.getDuracao());
    }

    private ServicoEntity toEntity(Servico servico) {
        ServicoEntity entity = new ServicoEntity();
        entity.setIdServico(servico.getIdServico());
        entity.setNome(servico.getNome());
        entity.setPreco(servico.getPreco());
        entity.setDescricao(servico.getDescricao());
        entity.setDuracao(servico.getDuracao());
        return entity;
    }

    @Override
    public Servico salvar(Servico servico) {
        ServicoEntity salvo = servicoJpaRepository.save(toEntity(servico));
        return toDomain(salvo);
    }

    @Override
    public List<Servico> listarTodos() {
        return servicoJpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Servico> buscarPorId(Integer id) {
        return servicoJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void deletar(Integer id) {
        servicoJpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Integer id) {
        return servicoJpaRepository.existsById(id);
    }

    @Override
    public List<Object[]> buscarTop3ServicosMaisAgendados() {
        return servicoJpaRepository.findTop3ServicosMaisAgendados();
    }

    @Override
    public List<Object[]> buscarTop3ServicosMenosAgendados() {
        return servicoJpaRepository.findTop3ServicosMenosAgendados();
    }
}
