package com.beiramar.beiramar.api.core.adapter;

import com.beiramar.beiramar.api.core.domain.Cargo;
import com.beiramar.beiramar.api.core.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioGateway {

    Usuario salvar(Usuario usuario);
    List<Usuario> listarTodos();
    List<Usuario> listarPorCargo(String nomeCargo);
    Optional<Usuario> buscarPorId(Integer id);
    Optional<Usuario> buscarPorEmail(String email);
    void deletar(Integer id);
    Cargo buscarCargoPorId(Integer id);

}
