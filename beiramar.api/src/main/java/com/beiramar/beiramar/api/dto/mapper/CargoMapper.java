package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.cargoDtos.CargoAtualizacaoDto;
import com.beiramar.beiramar.api.dto.cargoDtos.CargoCadastroDto;
import com.beiramar.beiramar.api.dto.cargoDtos.CargoListagemDto;
import com.beiramar.beiramar.api.entity.Cargo;

public class CargoMapper {

    public static Cargo toEntity(CargoCadastroDto dto){
        Cargo cargo = new Cargo();
        cargo.setNome(dto.getNome());
        return cargo;
    }

    public static CargoListagemDto toDto(Cargo cargo){
        return new CargoListagemDto(
                cargo.getIdCargo(),
                cargo.getNome()
        );
    }

    public static void atualizarEntity(Cargo cargo, CargoAtualizacaoDto dto){
        cargo.setNome(dto.getNome());
    }
}
