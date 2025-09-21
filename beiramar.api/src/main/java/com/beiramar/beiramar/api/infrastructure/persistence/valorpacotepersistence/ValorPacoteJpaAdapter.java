package com.beiramar.beiramar.api.infrastructure.persistence.valorpacotepersistence;

import com.beiramar.beiramar.api.core.adapter.ValorPacoteGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Cargo;
import com.beiramar.beiramar.api.core.domain.Pacote;
import com.beiramar.beiramar.api.core.domain.Usuario;
import com.beiramar.beiramar.api.core.domain.ValorPacote;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ValorPacoteJpaAdapter implements ValorPacoteGateway {


    private final ValorPacoteJpaRepository valorPacoteJpaRepository;
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final PacoteJpaRepository pacoteJpaRepository;

    public ValorPacoteJpaAdapter(ValorPacoteJpaRepository valorPacoteJpaRepository, UsuarioJpaRepository usuarioJpaRepository, PacoteJpaRepository pacoteJpaRepository) {
        this.valorPacoteJpaRepository = valorPacoteJpaRepository;
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.pacoteJpaRepository = pacoteJpaRepository;
    }

    private ValorPacote toDomain(ValorPacoteEntity entity){

        Cargo cargo = new Cargo(
                entity.getUsuario().getCargo().getIdCargo(),
                entity.getUsuario().getCargo().getNome()
        );
        Usuario usuario = new Usuario(
                entity.getUsuario().getIdUsuario(),
                entity.getUsuario().getNome(),
                entity.getUsuario().getEmail(),
                entity.getUsuario().getTelefone(),
                entity.getUsuario().getSenha(),
                entity.getUsuario().getDtNasc(),
                cargo,
                null
        );

        Pacote pacote = new Pacote(
                entity.getPacote().getIdPacote(),
                entity.getPacote().getNome(),
                entity.getPacote().getPrecoTotalSemDesconto(),
                entity.getPacote().getQtdSessoesTotal(),
                entity.getPacote().getTempoLimiteDias()
        );

        return new ValorPacote(
                entity.getIdValorPacote(),
                entity.getValorTotal(),
                usuario,
                pacote
        );
    }

    private ValorPacoteEntity toEntity(ValorPacote valorPacote) {
        UsuarioEntity usuarioEntity = usuarioJpaRepository.findById(valorPacote.getUsuario().getIdUsuario())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        PacoteEntity pacoteEntity = pacoteJpaRepository.findById(valorPacote.getPacote().getIdPacote())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pacote não encontrado"));

        ValorPacoteEntity entity = new ValorPacoteEntity();
        entity.setIdValorPacote(valorPacote.getIdValorPacote());
        entity.setValorTotal(valorPacote.getValorTotal());
        entity.setUsuario(usuarioEntity);
        entity.setPacote(pacoteEntity);

        return entity;
    }



    @Override
    public ValorPacote salvar(ValorPacote valorPacote) {
        ValorPacoteEntity salvo = valorPacoteJpaRepository.save(toEntity(valorPacote));
        return toDomain(salvo);
    }

    @Override
    public List<ValorPacote> listarTodos() {
        return valorPacoteJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ValorPacote> buscarPorId(Integer id) {
        return valorPacoteJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void deletar(Integer id) {
        valorPacoteJpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Integer id) {
        return valorPacoteJpaRepository.existsById(id);
    }


    @Override
    public Usuario buscarUsuarioPorId(Integer id) {
        return usuarioJpaRepository.findById(id)
                .map(entity -> new Usuario(
                        entity.getIdUsuario(),
                        entity.getNome(),
                        entity.getEmail(),
                        entity.getTelefone(),
                        entity.getSenha(),
                        entity.getDtNasc(),
                        new Cargo(
                                entity.getCargo().getIdCargo(),
                                entity.getCargo().getNome()
                        ),
                        null
                                        ))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com ID: " + id));
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
}
