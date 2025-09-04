package com.beiramar.beiramar.api.core.adapter;

import com.beiramar.beiramar.api.core.domain.Pacote;
import com.beiramar.beiramar.api.core.domain.Usuario;
import com.beiramar.beiramar.api.core.domain.ValorPacote;

import java.util.List;
import java.util.Optional;

public interface ValorPacoteGateway {

    ValorPacote salvar(ValorPacote valorPacote);
    List<ValorPacote> listarTodos();
    Optional<ValorPacote> buscarPorId(Integer id);
    void deletar(Integer id);
    boolean existePorId(Integer id);
    Usuario buscarUsuarioPorId (Integer id);
    Pacote buscarPacotePorId(Integer id);
}
