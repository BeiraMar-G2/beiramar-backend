package com.beiramar.beiramar.api.core.application.usecase.sessoespacoteusecase;

import com.beiramar.beiramar.api.core.adapter.SessoesPacoteGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

public class DeletarSessoesPacoteUseCase {

    private final SessoesPacoteGateway sessoesPacoteGateway;

    public DeletarSessoesPacoteUseCase(SessoesPacoteGateway sessoesPacoteGateway) {
        this.sessoesPacoteGateway = sessoesPacoteGateway;
    }

    public void executar(Integer id) {
        if (!sessoesPacoteGateway.existePorId(id)) {
            throw new EntidadeNaoEncontradaException("Sessões-Pacote não encontrado");
        }
        sessoesPacoteGateway.deletar(id);
    }
}
