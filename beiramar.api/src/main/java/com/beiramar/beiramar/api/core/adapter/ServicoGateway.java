package com.beiramar.beiramar.api.core.adapter;

import com.beiramar.beiramar.api.core.domain.Servico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ServicoGateway {

    Servico salvar(Servico servico);
    List<Servico> listarTodos();
    Optional<Servico> buscarPorId(Integer id);
    void deletar(Integer id);
    boolean existePorId(Integer id);
    List<Object[]> buscarTop3ServicosMaisAgendados(LocalDateTime dataInicio, LocalDateTime dataFim);
    List<Object[]> buscarTop3ServicosMenosAgendados(LocalDateTime dataInicio, LocalDateTime dataFim);
    List<Object[]> buscarServicosMaisCancelados(LocalDateTime dataInicio, LocalDateTime dataFim);
    List<Object[]> buscarAgendamentosPorDiaSemanaPorNomeServico(String nomeServico);
    List<Object[]> buscarCancelamentosPorDiaSemanaPorNomeServico(String nomeServico);
}
