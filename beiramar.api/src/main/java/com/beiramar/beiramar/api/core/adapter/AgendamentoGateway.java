package com.beiramar.beiramar.api.core.adapter;

import com.beiramar.beiramar.api.core.domain.Agendamento;
import com.beiramar.beiramar.api.core.domain.Pacote;
import com.beiramar.beiramar.api.core.domain.Servico;
import com.beiramar.beiramar.api.core.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface AgendamentoGateway {

    Agendamento salvar(Agendamento agendamento);
    List<Agendamento> listarTodos();
    Optional<Agendamento> buscarPorId(Integer id);
    void deletar(Integer id);
    boolean existePorId(Integer id);
    Pacote buscarPacotePorId(Integer id);
    Servico buscarServicoPorId(Integer id);
    Usuario buscarClientePorId(Integer id);
    Usuario buscarFuncionarioPorId(Integer id);
    List<Agendamento> listarPorIdCliente(Integer idCliente);
    List<Agendamento> listarPorMes(Integer mes, Integer ano);
    Long contarAgendamentosComStatusAgendadoPorDias(Integer dias);
    Long contarAgendamentosCanceladosPorDias(Integer dias);
}
