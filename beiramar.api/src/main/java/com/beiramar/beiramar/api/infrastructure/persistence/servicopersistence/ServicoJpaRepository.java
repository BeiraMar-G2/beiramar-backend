package com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoJpaRepository extends JpaRepository<ServicoEntity, Integer> {
}
