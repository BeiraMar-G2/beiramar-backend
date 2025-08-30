package com.beiramar.beiramar.api.core.application.usecase.cargousecase;

import com.beiramar.beiramar.api.core.adapter.CargoGateway;
import com.beiramar.beiramar.api.core.application.command.cargocommand.CargoCadastroCommand;
import com.beiramar.beiramar.api.core.domain.Cargo;

public class CadastrarCargoUseCase {

    private final CargoGateway cargoGateway;

    public CadastrarCargoUseCase(CargoGateway cargoGateway) {
        this.cargoGateway = cargoGateway;
    }

    public Cargo executar(CargoCadastroCommand command) {
        Cargo cargo = new Cargo(command.getNome());
        return cargoGateway.salvar(cargo);
    }
}
