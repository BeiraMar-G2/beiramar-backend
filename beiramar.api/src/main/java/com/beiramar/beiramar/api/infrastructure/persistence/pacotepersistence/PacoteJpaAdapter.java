package com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence;

import com.beiramar.beiramar.api.core.adapter.PacoteGateway;
import com.beiramar.beiramar.api.core.domain.Pacote;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PacoteJpaAdapter implements PacoteGateway {

    private final PacoteJpaRepository pacoteJpaRepository;

    public PacoteJpaAdapter(PacoteJpaRepository pacoteJpaRepository) {
        this.pacoteJpaRepository = pacoteJpaRepository;
    }

    private Pacote toDomain(PacoteEntity entity){
        return new Pacote(entity.getIdPacote(), entity.getNome(), entity.getPrecoTotalSemDesconto(),
                 entity.getQtdSessoesTotal(), entity.getTempoLimiteDias());
    }

    private PacoteEntity toEntity(Pacote pacote){
        PacoteEntity entity = new PacoteEntity();
        entity.setIdPacote(pacote.getIdPacote());
        entity.setNome(pacote.getNome());
        entity.setPrecoTotalSemDesconto(pacote.getPrecoTotalSemDesconto());
        entity.setQtdSessoesTotal(pacote.getQtdSessoesTotal());
        entity.setTempoLimiteDias(pacote.getTempoLimiteDias());
        return entity;
    }


    @Override
    public Pacote salvar(Pacote pacote) {
        PacoteEntity salvo = pacoteJpaRepository.save(toEntity(pacote));
        return toDomain(salvo);
    }

    @Override
    public List<Pacote> listarTodos() {
        return pacoteJpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pacote> buscarPorId(Integer id) {
        return pacoteJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void deletar(Integer id) {
        pacoteJpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Integer id) {
        return pacoteJpaRepository.existsById(id);
    }
}
