package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;

public class ContarAgendamentoStatusCanceladoUseCase {
    private final AgendamentoGateway agendamentoGateway;

    public ContarAgendamentoStatusCanceladoUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    public Long executarCancelados(Integer dias) {
        if (dias == null || dias <= 0) {
            throw new IllegalArgumentException("O número de dias deve ser maior que zero");
        }

        return agendamentoGateway.contarAgendamentosCanceladosPorDias(dias);
    }

}
