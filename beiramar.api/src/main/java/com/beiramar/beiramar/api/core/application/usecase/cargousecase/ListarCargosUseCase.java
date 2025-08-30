package com.beiramar.beiramar.api.core.application.usecase.cargousecase;

import com.beiramar.beiramar.api.core.adapter.CargoGateway;
import com.beiramar.beiramar.api.core.domain.Cargo;

import java.util.List;

public class ListarCargosUseCase {

    private final CargoGateway cargoGateway;

    public ListarCargosUseCase(CargoGateway cargoGateway) {
        this.cargoGateway = cargoGateway;
    }

    public List<Cargo> executar() {
        return cargoGateway.listarTodos();
    }
}
