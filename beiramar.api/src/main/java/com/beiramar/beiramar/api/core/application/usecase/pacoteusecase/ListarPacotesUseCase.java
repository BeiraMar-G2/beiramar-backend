package com.beiramar.beiramar.api.core.application.usecase.pacoteusecase;

import com.beiramar.beiramar.api.core.adapter.PacoteGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.core.domain.Pacote;

import java.util.List;

public class ListarPacotesUseCase {

    private final PacoteGateway pacoteGateway;

    public ListarPacotesUseCase(PacoteGateway pacoteGateway) {
        this.pacoteGateway = pacoteGateway;
    }

    public List<Pacote> executar(){

        List<Pacote> pacotes = pacoteGateway.listarTodos();

        if (pacotes.isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhum Pacote cadastrado");
        }
        return pacotes;
    }
}
