package com.beiramar.beiramar.api.infrastructure.persistence.disponibilidadepersistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisponibilidadeJpaRepository extends JpaRepository<DisponibilidadeEntity, Integer> {
    List<DisponibilidadeEntity> findByFuncionario_Nome(String nome);
}
