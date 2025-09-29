package com.beiramar.beiramar.api.core.adapter;

import com.beiramar.beiramar.api.core.domain.Servico;

import java.util.List;
import java.util.Optional;

public interface ServicoGateway {

    Servico salvar(Servico servico);
    List<Servico> listarTodos();
    Optional<Servico> buscarPorId(Integer id);
    void deletar(Integer id);
    boolean existePorId(Integer id);
    List<Object[]> buscarTop3ServicosMaisAgendados();
    List<Object[]> buscarTop3ServicosMenosAgendados();
    List<Object[]> buscarServicosMaisCancelados();
    List<Object[]> buscarAgendamentosPorDiaSemanaPorNomeServico(String nomeServico);
    List<Object[]> buscarCancelamentosPorDiaSemanaPorNomeServico(String nomeServico);
}
