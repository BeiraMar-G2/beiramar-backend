package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoAtualizacaoDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoCadastroDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoListagemDto;
import com.beiramar.beiramar.api.infrastructure.persistence.agendamentopersistence.AgendamentoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;

public class AgendamentoMapper {

    public static AgendamentoEntity toEntity(AgendamentoCadastroDto dto, UsuarioEntity cliente, UsuarioEntity funcionario, ServicoEntity servico, PacoteEntity pacote) {
        AgendamentoEntity agendamento = new AgendamentoEntity();
        agendamento.setCliente(cliente);
        agendamento.setFuncionario(funcionario);
        agendamento.setServico(servico);
        agendamento.setPacote(pacote);
        agendamento.setDtHora(dto.getDtHora());
        agendamento.setValorPago(dto.getValorPago());
        agendamento.setStatus(dto.getStatus());
        return agendamento;
    }

    public static AgendamentoListagemDto toDto(AgendamentoEntity agendamento) {
        return new AgendamentoListagemDto(
                agendamento.getIdAgendamento(),
                agendamento.getCliente().getNome(),
                agendamento.getFuncionario().getNome(),
                agendamento.getServico().getNome(),
                agendamento.getDtHora(),
                agendamento.getValorPago(),
                agendamento.getStatus()
        );
    }

    public static void AtualizarEntity(AgendamentoEntity agendamento, AgendamentoAtualizacaoDto dto) {
        agendamento.setDtHora(dto.getDtHora());
        agendamento.setValorPago(dto.getValorPago());
        agendamento.setStatus(dto.getStatus());
    }
}
