package com.beiramar.beiramar.api.core.adapter;

import com.beiramar.beiramar.api.core.domain.Cargo;

import java.util.List;
import java.util.Optional;

public interface CargoGateway {

    Cargo salvar(Cargo cargo);
    List<Cargo> listarTodos();
    Optional<Cargo> buscarPorId(Integer id);
    Optional<Cargo> buscarPorNome(String nome);
    void deletar(Integer id);
    boolean existePorId(Integer id);
}
