package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.cargoDtos.CargoAtualizacaoDto;
import com.beiramar.beiramar.api.dto.cargoDtos.CargoCadastroDto;
import com.beiramar.beiramar.api.dto.cargoDtos.CargoListagemDto;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoEntity;

public class CargoMapper {

    public static CargoEntity toEntity(CargoCadastroDto dto){
        CargoEntity cargo = new CargoEntity();
        cargo.setNome(dto.getNome());
        return cargo;
    }

    public static CargoListagemDto toDto(CargoEntity cargo){
        return new CargoListagemDto(
                cargo.getIdCargo(),
                cargo.getNome()
        );
    }

    public static void atualizarEntity(CargoEntity cargo, CargoAtualizacaoDto dto){
        cargo.setNome(dto.getNome());
    }
}
