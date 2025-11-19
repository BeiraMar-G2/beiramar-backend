package com.beiramar.beiramar.api.core.application.usecase.servicousecase;

import com.beiramar.beiramar.api.core.adapter.ServicoGateway;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;

import java.time.LocalDateTime;
import java.util.List;

public class BuscarServicosMaisCanceladosUseCase {
    private final ServicoGateway servicoGateway;

    public BuscarServicosMaisCanceladosUseCase(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    public List<Object[]> executar(LocalDateTime dataInicio, LocalDateTime dataFim) {

        List<Object[]> maisCancelados =
                servicoGateway.buscarServicosMaisCancelados(dataInicio, dataFim);

        if (maisCancelados.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum serviço encontrado");
        }

        return maisCancelados;
    }

}
