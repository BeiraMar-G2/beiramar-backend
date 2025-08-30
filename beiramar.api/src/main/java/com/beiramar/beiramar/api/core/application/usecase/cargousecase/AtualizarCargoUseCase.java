package com.beiramar.beiramar.api.core.application.usecase.cargousecase;

import com.beiramar.beiramar.api.core.adapter.CargoGateway;
import com.beiramar.beiramar.api.core.application.command.cargocommand.CargoAtualizacaoCommand;
import com.beiramar.beiramar.api.core.domain.Cargo;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

public class AtualizarCargoUseCase {

    private final CargoGateway cargoGateway;

    public AtualizarCargoUseCase(CargoGateway cargoGateway) {
        this.cargoGateway = cargoGateway;
    }

    public Cargo executar(Integer id, CargoAtualizacaoCommand command) {
        Cargo cargo = cargoGateway.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cargo não encontrado"));
        cargo.atualizarNome(command.getNome());
        return cargoGateway.salvar(cargo);
    }
}
