package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.domain.Servico;

import java.util.List;

public class ListarServicosUseCase {

    private final ServicoGateway servicoGateway;

    public ListarServicosUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    public List<Servico> executar() {
        return servicoGateway.listarTodos();
    }
}
