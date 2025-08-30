package com.beiramar.beiramar.api.infrastructure.di;

import com.beiramar.beiramar.api.core.adapter.CargoGateway;
import com.beiramar.beiramar.api.core.application.usecase.cargousecase.*;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoJpaAdapter;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CargoBeanConfig {


    @Bean
    public CargoGateway cargoGateway(CargoJpaRepository cargoJpaRepository) {
        return new CargoJpaAdapter(cargoJpaRepository);
    }

    @Bean
    public CadastrarCargoUseCase cadastrarCargoUseCase(CargoGateway cargoGateway) {
        return new CadastrarCargoUseCase(cargoGateway);
    }

    @Bean
    public ListarCargosUseCase listarCargosUseCase(CargoGateway cargoGateway) {
        return new ListarCargosUseCase(cargoGateway);
    }

    @Bean
    public BuscarCargoPorIdUseCase buscarCargoPorIdUseCase(CargoGateway cargoGateway) {
        return new BuscarCargoPorIdUseCase(cargoGateway);
    }

    @Bean
    public AtualizarCargoUseCase atualizarCargoUseCase(CargoGateway cargoGateway) {
        return new AtualizarCargoUseCase(cargoGateway);
    }

    @Bean
    public DeletarCargoUseCase deletarCargoUseCase(CargoGateway cargoGateway) {
        return new DeletarCargoUseCase(cargoGateway);
    }
}
