package com.beiramar.beiramar.api.repository;

import com.beiramar.beiramar.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
