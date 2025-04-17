package com.beiramar.beiramar.api.repository;

import com.beiramar.beiramar.api.entity.TipoUsuarioEnum;
import com.beiramar.beiramar.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findAllByTipoUsuario(TipoUsuarioEnum tipoUsuario);
}
