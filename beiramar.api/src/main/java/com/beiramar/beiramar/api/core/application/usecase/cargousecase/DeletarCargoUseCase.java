package com.beiramar.beiramar.api.core.application.usecase.cargousecase;

import com.beiramar.beiramar.api.core.adapter.CargoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

public class DeletarCargoUseCase {

    private final CargoGateway cargoGateway;

    public DeletarCargoUseCase(CargoGateway cargoGateway) {
        this.cargoGateway = cargoGateway;
    }

    public void executar(Integer id) {
        if (!cargoGateway.existePorId(id)) {
            throw new EntidadeNaoEncontradaException("Cargo não encontrado");
        }
        cargoGateway.deletar(id);
    }
}
