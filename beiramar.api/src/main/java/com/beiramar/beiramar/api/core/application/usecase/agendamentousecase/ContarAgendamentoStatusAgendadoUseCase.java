package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;

public class ContarAgendamentoStatusAgendadoUseCase {

    private final AgendamentoGateway agendamentoGateway;

    public ContarAgendamentoStatusAgendadoUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    /**
     * Conta a quantidade de agendamentos com status "Agendado" nos últimos X dias
     * @param dias Número de dias para filtrar
     * @return Quantidade de agendamentos agendados
     */
    public Long executar(Integer dias) {
        if (dias == null || dias <= 0) {
            throw new IllegalArgumentException("O número de dias deve ser maior que zero");
        }

        return agendamentoGateway.contarAgendamentosComStatusAgendadoPorDias(dias);
    }

    /**
     * Conta a quantidade de agendamentos com status "Cancelado" nos últimos X dias
     * @param dias Número de dias para filtrar
     * @return Quantidade de agendamentos cancelados
     */
    public Long executarCancelados(Integer dias) {
        if (dias == null || dias <= 0) {
            throw new IllegalArgumentException("O número de dias deve ser maior que zero");
        }

        return agendamentoGateway.contarAgendamentosCanceladosPorDias(dias);
    }
}
