package com.beiramar.beiramar.api.core.application.usecase.cargousecase;

import com.beiramar.beiramar.api.core.adapter.CargoGateway;
import com.beiramar.beiramar.api.core.domain.Cargo;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

public class BuscarCargoPorIdUseCase {

    private final CargoGateway cargoGateway;

    public BuscarCargoPorIdUseCase(CargoGateway cargoGateway) {
        this.cargoGateway = cargoGateway;
    }

    public Cargo executar(Integer id) {
        return cargoGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo não encontrado"));
    }
}
