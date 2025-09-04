package com.beiramar.beiramar.api.core.application.usecase.sessoespacoteusecase;

import com.beiramar.beiramar.api.core.adapter.SessoesPacoteGateway;
import com.beiramar.beiramar.api.core.domain.SessoesPacote;
import com.beiramar.beiramar.api.core.domain.ValorPacote;

import java.util.List;

public class ListarSessoesPacoteUseCase {

    private final SessoesPacoteGateway sessoesPacoteGateway;

    public ListarSessoesPacoteUseCase(SessoesPacoteGateway sessoesPacoteGateway) {
        this.sessoesPacoteGateway = sessoesPacoteGateway;
    }

    public List<SessoesPacote> executar(){
        return sessoesPacoteGateway.listarTodos();
        }
}
