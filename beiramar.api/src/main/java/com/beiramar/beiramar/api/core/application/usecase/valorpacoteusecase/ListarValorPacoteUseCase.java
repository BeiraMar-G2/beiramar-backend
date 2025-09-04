package com.beiramar.beiramar.api.core.application.usecase.valorpacoteusecase;

import com.beiramar.beiramar.api.core.adapter.ValorPacoteGateway;
import com.beiramar.beiramar.api.core.domain.ValorPacote;

import java.util.List;

public class ListarValorPacoteUseCase {

    private final ValorPacoteGateway valorPacoteGateway;

    public ListarValorPacoteUseCase(ValorPacoteGateway valorPacoteGateway) {
        this.valorPacoteGateway = valorPacoteGateway;
    }

    public List<ValorPacote> executar(){
        return valorPacoteGateway.listarTodos();
    }
}
