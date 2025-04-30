package com.beiramar.beiramar.api.repository;

import com.beiramar.beiramar.api.entity.Cargo;
import com.beiramar.beiramar.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByCargo(Cargo cargo);
    Optional<Usuario> findByEmail(String email);
}
