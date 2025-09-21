package com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence;

import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Integer> {

    List<UsuarioEntity> findByCargo(CargoEntity cargo);
    Optional<UsuarioEntity> findByEmail(String email);
    List<UsuarioEntity> findByCargo_Nome(String nomeCargo);

}
