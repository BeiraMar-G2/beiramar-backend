package com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence;

import com.beiramar.beiramar.api.core.adapter.UsuarioGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Cargo;
import com.beiramar.beiramar.api.core.domain.Usuario;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioJpaAdapter implements UsuarioGateway {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final CargoJpaRepository cargoJpaRepository;


    public UsuarioJpaAdapter(UsuarioJpaRepository usuarioJpaRepository, CargoJpaRepository cargoJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.cargoJpaRepository = cargoJpaRepository;
    }


    private Usuario toDomain(UsuarioEntity entity) {
        Cargo cargo = new Cargo(entity.getCargo().getIdCargo(), entity.getCargo().getNome());
        return new Usuario(entity.getIdUsuario(), entity.getNome(), entity.getEmail(), entity.getTelefone(), entity.getSenha(), entity.getDtNasc(), cargo);
    }

    private UsuarioEntity toEntity(Usuario usuario) {

        UsuarioEntity entity = new UsuarioEntity();

        entity.setIdUsuario(usuario.getIdUsuario());
        entity.setNome(usuario.getNome());
        entity.setEmail(usuario.getEmail());
        entity.setTelefone(usuario.getTelefone());
        entity.setSenha(usuario.getSenha());
        entity.setDtNasc(usuario.getDtNasc());

        CargoEntity cargoEntity = new CargoEntity();
        cargoEntity.setIdCargo(usuario.getCargo().getIdCargo());
        entity.setCargo(cargoEntity);

        return entity;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        CargoEntity cargoEntity = cargoJpaRepository.findById(usuario.getCargo().getIdCargo())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo não encontrado com id: " + usuario.getCargo().getIdCargo()));

        UsuarioEntity entity = toEntity(usuario);
        entity.setCargo(cargoEntity);
        UsuarioEntity salvo = usuarioJpaRepository.save(entity);
        return toDomain(salvo);
    }


    @Override
    public List<Usuario> listarPorCargo(String nomeCargo) {
        CargoEntity cargo = new CargoEntity();
        cargo.setNome(nomeCargo);
        return usuarioJpaRepository.findByCargo(cargo).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioJpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioJpaRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public void deletar(Integer id) {
        usuarioJpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Integer id) {
        return usuarioJpaRepository.existsById(id);
    }

    @Override
    public Cargo buscarCargoPorId(Integer id) {
        return cargoJpaRepository.findById(id)
                .map(cargoEntity -> new Cargo(cargoEntity.getIdCargo(), cargoEntity.getNome()))
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));
    }

}
