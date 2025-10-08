package com.beiramar.beiramar.api.infrastructure.di;

import com.beiramar.beiramar.api.core.adapter.AgendamentoGateway;
import com.beiramar.beiramar.api.core.application.usecase.agendamentousecase.*;
import com.beiramar.beiramar.api.infrastructure.persistence.agendamentopersistence.AgendamentoJpaAdapter;
import com.beiramar.beiramar.api.infrastructure.persistence.agendamentopersistence.AgendamentoJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgendamentoBeanConfig {


    @Bean
    public AgendamentoGateway agendamentoGateway(
            AgendamentoJpaRepository agendamentoRepository,
            UsuarioJpaRepository usuarioRepository,
            ServicoJpaRepository servicoRepository,
            PacoteJpaRepository pacoteRepository
    ) {
        return new AgendamentoJpaAdapter(agendamentoRepository, usuarioRepository, servicoRepository, pacoteRepository);
    }

    @Bean
    public CadastrarAgendamentoUseCase cadastrarAgendamentoUseCase(AgendamentoGateway gateway) {
        return new CadastrarAgendamentoUseCase(gateway);
    }

    @Bean
    public AtualizarAgendamentoUseCase atualizarAgendamentoUseCase(AgendamentoGateway gateway) {
        return new AtualizarAgendamentoUseCase(gateway);
    }

    @Bean
    public BuscarAgendamentoPorIdUseCase buscarAgendamentoPorIdUseCase(AgendamentoGateway gateway) {
        return new BuscarAgendamentoPorIdUseCase(gateway);
    }

    @Bean
    public DeletarAgendamentoUseCase deletarAgendamentoUseCase(AgendamentoGateway gateway) {
        return new DeletarAgendamentoUseCase(gateway);
    }

    @Bean
    public ListarAgendamentosUseCase listarAgendamentosUseCase(AgendamentoGateway gateway) {
        return new ListarAgendamentosUseCase(gateway);
    }

    @Bean
    public ListarAgendamentosPaginadoUseCase listarAgendamentosPaginadoUseCase(AgendamentoGateway gateway) {
        return new ListarAgendamentosPaginadoUseCase(gateway);
    }

    @Bean
    public ListarAgendamentosPorIdClienteUseCase listarAgendamentosPorIdClienteUseCase(AgendamentoGateway gateway) {
        return new ListarAgendamentosPorIdClienteUseCase(gateway);
    }

    @Bean
    public ListarAgendamentosPorIdClientePaginadoUseCase listarAgendamentosPorIdClientePaginadoUseCase(AgendamentoGateway gateway) {
        return new ListarAgendamentosPorIdClientePaginadoUseCase(gateway);
    }

    @Bean
    public ListarAgendamentosPorMesUseCase listarAgendamentosPorMesUseCase(AgendamentoGateway gateway) {
        return new ListarAgendamentosPorMesUseCase(gateway);
    }

    @Bean
    public ListarAgendamentosPorMesPaginadoUseCase listarAgendamentosPorMesPaginadoUseCase(AgendamentoGateway gateway) {
        return new ListarAgendamentosPorMesPaginadoUseCase(gateway);
    }

    @Bean
    public ContarAgendamentoStatusAgendadoUseCase contarAgendamentoStatusAgendadoUseCase(AgendamentoGateway gateway) {
        return new ContarAgendamentoStatusAgendadoUseCase(gateway);
    }
}
