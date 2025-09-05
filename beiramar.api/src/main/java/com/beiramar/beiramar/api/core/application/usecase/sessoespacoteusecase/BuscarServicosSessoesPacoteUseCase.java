package com.beiramar.beiramar.api.core.application.usecase.sessoespacoteusecase;

import com.beiramar.beiramar.api.core.adapter.SessoesPacoteGateway;
import com.beiramar.beiramar.api.core.domain.Servico;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarServicosSessoesPacoteUseCase {

    private final SessoesPacoteGateway sessoesPacoteGateway;

    public BuscarServicosSessoesPacoteUseCase(SessoesPacoteGateway sessoesPacoteGateway) {
        this.sessoesPacoteGateway = sessoesPacoteGateway;
    }

    public List<Servico> executar(Integer idPacote) {
        return sessoesPacoteGateway.buscarServicosPorPacote(idPacote);
    }
}
