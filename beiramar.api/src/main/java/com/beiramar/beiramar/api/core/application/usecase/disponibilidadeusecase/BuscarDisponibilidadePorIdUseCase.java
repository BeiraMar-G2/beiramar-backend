package com.beiramar.beiramar.api.core.application.usecase.disponibilidadeusecase;

import com.beiramar.beiramar.api.core.adapter.DisponibilidadeGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Agendamento;
import com.beiramar.beiramar.api.core.domain.Disponibilidade;

public class BuscarDisponibilidadePorIdUseCase {

    private final DisponibilidadeGateway disponibilidadeGateway;

    public BuscarDisponibilidadePorIdUseCase(DisponibilidadeGateway disponibilidadeGateway) {
        this.disponibilidadeGateway = disponibilidadeGateway;
    }

    public Disponibilidade executar(Integer id) {
        return disponibilidadeGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Disponibilidade não encontrada"));
    }
}
