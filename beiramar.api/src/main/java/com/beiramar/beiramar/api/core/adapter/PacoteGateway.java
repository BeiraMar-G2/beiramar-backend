package com.beiramar.beiramar.api.core.adapter;

import com.beiramar.beiramar.api.core.domain.Pacote;
import com.beiramar.beiramar.api.core.domain.Servico;

import java.util.List;
import java.util.Optional;

public interface PacoteGateway {

    Pacote salvar(Pacote pacote);
    List<Pacote> listarTodos();
    Optional<Pacote> buscarPorId(Integer id);
    void deletar(Integer id);
    boolean existePorId(Integer id);
}
