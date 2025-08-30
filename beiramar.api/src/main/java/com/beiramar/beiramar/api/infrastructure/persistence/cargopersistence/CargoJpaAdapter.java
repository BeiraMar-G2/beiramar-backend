package com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence;

import com.beiramar.beiramar.api.core.adapter.CargoGateway;
import com.beiramar.beiramar.api.core.domain.Cargo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CargoJpaAdapter implements CargoGateway {

    private final CargoJpaRepository cargoJpaRepository;

    public CargoJpaAdapter(CargoJpaRepository cargoJpaRepository) {
        this.cargoJpaRepository = cargoJpaRepository;
    }

    private Cargo toDomain(CargoEntity entity) {
        return new Cargo(entity.getIdCargo(), entity.getNome());
    }

    private CargoEntity toEntity(Cargo cargo) {
        CargoEntity entity = new CargoEntity();
        entity.setIdCargo(cargo.getIdCargo());
        entity.setNome(cargo.getNome());
        return entity;
    }

    @Override
    public Cargo salvar(Cargo cargo) {
        CargoEntity salvo = cargoJpaRepository.save(toEntity(cargo));
        return toDomain(salvo);
    }

    @Override
    public List<Cargo> listarTodos() {
        return cargoJpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cargo> buscarPorId(Integer id) {
        return cargoJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Cargo> buscarPorNome(String nome) {
        return cargoJpaRepository.findByNomeIgnoreCase(nome).map(this::toDomain);
    }

    @Override
    public void deletar(Integer id) {
        cargoJpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Integer id) {
        return cargoJpaRepository.existsById(id);
    }

}
