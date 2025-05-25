package com.beiramar.beiramar.api.dto.mapper;

import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoAtualizacaoDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoCadastroDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoListagemDto;
import com.beiramar.beiramar.api.entity.Agendamento;
import com.beiramar.beiramar.api.entity.Pacote;
import com.beiramar.beiramar.api.entity.Servico;
import com.beiramar.beiramar.api.entity.Usuario;

public class AgendamentoMapper {

    public static Agendamento toEntity(AgendamentoCadastroDto dto, Usuario cliente, Usuario funcionario, Servico servico, Pacote pacote) {
        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setFuncionario(funcionario);
        agendamento.setServico(servico);
        agendamento.setPacote(pacote);
        agendamento.setDtHora(dto.getDtHora());
        agendamento.setValorPago(dto.getValorPago());
        agendamento.setStatus(dto.getStatus());
        return agendamento;
    }

    public static AgendamentoListagemDto toDto(Agendamento agendamento) {
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

    public static void AtualizarEntity(Agendamento agendamento, AgendamentoAtualizacaoDto dto) {
        agendamento.setDtHora(dto.getDtHora());
        agendamento.setValorPago(dto.getValorPago());
        agendamento.setStatus(dto.getStatus());
    }
}
