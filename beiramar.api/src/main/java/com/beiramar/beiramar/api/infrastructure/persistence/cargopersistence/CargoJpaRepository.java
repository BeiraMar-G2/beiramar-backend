package com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CargoJpaRepository extends JpaRepository<CargoEntity, Integer> {

    Optional<CargoEntity> findByNomeIgnoreCase(String nome);
}
