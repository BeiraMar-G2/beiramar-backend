package com.beiramar.beiramar.api.core.application.usecase.disponibilidadeusecase;

import com.beiramar.beiramar.api.core.adapter.DisponibilidadeGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

public class DeletarDisponibilidadeUseCase {

    private final DisponibilidadeGateway disponibilidadeGateway;

    public DeletarDisponibilidadeUseCase(DisponibilidadeGateway disponibilidadeGateway) {
        this.disponibilidadeGateway = disponibilidadeGateway;
    }

    public void executar(Integer id) {
        if (!disponibilidadeGateway.existePorId(id)) {
            throw new EntidadeNaoEncontradaException("Disponibilidade não encontrada");
        }
        disponibilidadeGateway.deletar(id);
    }
}
