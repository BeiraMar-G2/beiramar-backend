package com.beiramar.beiramar.api.core.application.usecase.disponibilidadeusecase;

import com.beiramar.beiramar.api.core.adapter.DisponibilidadeGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Disponibilidade;

import java.util.List;

public class ListarDisponibilidadesUseCase {

    private final DisponibilidadeGateway disponibilidadeGateway;

    public ListarDisponibilidadesUseCase(DisponibilidadeGateway disponibilidadeGateway) {
        this.disponibilidadeGateway = disponibilidadeGateway;
    }

    public List<Disponibilidade> executar(){
        List<Disponibilidade> disponibilidades = disponibilidadeGateway.listarTodos();

        if (disponibilidades.isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhuma disponibilidade cadastrada");
        }
        return disponibilidades;
    }
}
