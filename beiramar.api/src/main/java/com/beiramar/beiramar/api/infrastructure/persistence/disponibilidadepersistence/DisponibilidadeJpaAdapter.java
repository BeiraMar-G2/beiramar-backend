package com.beiramar.beiramar.api.infrastructure.persistence.disponibilidadepersistence;

import com.beiramar.beiramar.api.core.adapter.DisponibilidadeGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Disponibilidade;
import com.beiramar.beiramar.api.core.domain.Usuario;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DisponibilidadeJpaAdapter implements DisponibilidadeGateway {

    private final DisponibilidadeJpaRepository disponibilidadeJpaRepository;
    private final UsuarioJpaRepository usuarioJpaRepository;

    public DisponibilidadeJpaAdapter(DisponibilidadeJpaRepository disponibilidadeJpaRepository, UsuarioJpaRepository usuarioJpaRepository) {
        this.disponibilidadeJpaRepository = disponibilidadeJpaRepository;
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    private Disponibilidade toDomain(DisponibilidadeEntity entity){
        Usuario funcionario = entity.getFuncionario() != null ? toDomain(entity.getFuncionario()) : null;

        return new Disponibilidade(
                entity.getIdDisponibilidade(),
                entity.getDiaSemana(),
                entity.getHoraInicio(),
                entity.getHoraFim(),
                entity.getDiaMes(),
                funcionario,
                entity.getDisponibilidadeExcecao(),
                entity.getFkFuncionarioExcecaoNome()
        );
    }

    private DisponibilidadeEntity toEntity(Disponibilidade domain){
        DisponibilidadeEntity entity = new DisponibilidadeEntity();

        entity.setIdDisponibilidade(domain.getIdDisponibilidade());
        entity.setDiaSemana(domain.getDiaSemana());
        entity.setHoraInicio(domain.getHoraInicio());
        entity.setHoraFim(domain.getHoraFim());
        entity.setDiaMes(domain.getDiaMes());
        entity.setDisponibilidadeExcecao(domain.getDisponibilidadeExcecao());
        entity.setFkFuncionarioExcecaoNome(domain.getFkFuncionarioExcecaoNome());

        if (domain.getFuncionario() != null) {
            entity.setFuncionario(usuarioJpaRepository.findById(domain.getFuncionario().getIdUsuario())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado")));
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
                entity.getCargo() != null ? new com.beiramar.beiramar.api.core.domain.Cargo(entity.getCargo().getIdCargo(), entity.getCargo().getNome()) : null,
                null
        );
    }

    @Override
    public Disponibilidade salvar(Disponibilidade disponibilidade) {
        DisponibilidadeEntity entity = toEntity(disponibilidade);
        DisponibilidadeEntity salvo = disponibilidadeJpaRepository.save(entity);
        return toDomain(salvo);
    }

    @Override
    public List<Disponibilidade> listarTodos() {
        return disponibilidadeJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Disponibilidade> buscarPorId(Integer id) {
        return disponibilidadeJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void deletar(Integer id) {
        if (!disponibilidadeJpaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("disponibilidade não encontrado com id: " + id);
        }
        disponibilidadeJpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Integer id) {
        return disponibilidadeJpaRepository.existsById(id);
    }

    @Override
    public Usuario buscarFuncionarioPorId(Integer id) {
        UsuarioEntity entity = usuarioJpaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionário não encontrado com id: " + id));
        return toDomain(entity);
    }
}
