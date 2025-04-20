package com.beiramar.beiramar.api.repository;

import com.beiramar.beiramar.api.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {

    Optional<Cargo> findByNomeIgnoreCase(String nome);
}
