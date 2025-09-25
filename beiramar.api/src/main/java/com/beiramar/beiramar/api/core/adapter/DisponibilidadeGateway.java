package com.beiramar.beiramar.api.core.adapter;

import com.beiramar.beiramar.api.core.domain.Disponibilidade;
import com.beiramar.beiramar.api.core.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface DisponibilidadeGateway {

    Disponibilidade salvar(Disponibilidade disponibilidade);
    List<Disponibilidade> listarTodos();
    Optional<Disponibilidade> buscarPorId(Integer id);
    void deletar(Integer id);
    boolean existePorId(Integer id);
    Usuario buscarFuncionarioPorId(Integer id);

}
