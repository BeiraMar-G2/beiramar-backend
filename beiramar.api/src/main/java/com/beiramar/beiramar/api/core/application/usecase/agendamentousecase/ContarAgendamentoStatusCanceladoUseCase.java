package com.beiramar.beiramar.api.core.application.usecase.agendamentousecase;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;

import java.time.LocalDateTime;

public class ContarAgendamentoStatusCanceladoUseCase {
    private final AgendamentoGateway agendamentoGateway;

    public ContarAgendamentoStatusCanceladoUseCase(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    public Long executar(LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("Datas não podem ser nulas");
        }
        if (dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("dataInicio não pode ser depois de dataFim");
        }

        return agendamentoGateway.contarAgendamentosCanceladosPorPeriodo(dataInicio, dataFim);
    }

}
