    package com.beiramar.beiramar.api.core.application.usecase.valorpacoteusecase;

    import com.beiramar.beiramar.api.core.adapter.ValorPacoteGateway;
    import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
    import com.beiramar.beiramar.api.core.domain.Usuario;
    import com.beiramar.beiramar.api.core.domain.ValorPacote;

    public class BuscarValorPacotePorIdUseCase {

        private final ValorPacoteGateway valorPacoteGateway;

        public BuscarValorPacotePorIdUseCase(ValorPacoteGateway valorPacoteGateway) {
            this.valorPacoteGateway = valorPacoteGateway;
        }

        public ValorPacote executar(Integer id) {
            return valorPacoteGateway.buscarPorId(id)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Valor-Pacote não encontrado"));
        }
    }
