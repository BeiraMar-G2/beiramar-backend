package com.beiramar.beiramar.api.core.adapter;

import com.beiramar.beiramar.api.core.domain.Pacote;
import com.beiramar.beiramar.api.core.domain.Servico;
import com.beiramar.beiramar.api.core.domain.SessoesPacote;
import com.beiramar.beiramar.api.core.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface SessoesPacoteGateway {

    SessoesPacote salvar(SessoesPacote sessoesPacote);
    List<SessoesPacote> listarTodos();
    Optional<SessoesPacote> buscarPorId(Integer id);
    void deletar(Integer id);
    boolean existePorId(Integer id);
    Pacote buscarPacotePorId(Integer id);
    Servico buscarServicoPorId(Integer id);
}
