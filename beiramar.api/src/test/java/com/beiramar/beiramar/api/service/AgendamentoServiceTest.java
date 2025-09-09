package com.beiramar.beiramar.api.service;


import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoAtualizacaoDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoCadastroDto;
import com.beiramar.beiramar.api.dto.agendamentosDtos.AgendamentoListagemDto;
import com.beiramar.beiramar.api.dto.mapper.AgendamentoMapper;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.infrastructure.persistence.agendamentopersistence.AgendamentoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.agendamentopersistence.AgendamentoJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {
    @InjectMocks
    private AgendamentoService agendamentoService;


    @Mock
    private UsuarioJpaRepository usuarioRepository;

    @Mock
    private ServicoJpaRepository servicoRepository;

    @Mock
    private PacoteJpaRepository pacoteRepository;

    @Mock
    private AgendamentoJpaRepository agendamentoRepository;

    @Test
    void deveCadastrarAgendamentoComSucesso() {
        // Arrange - dados simulados
        AgendamentoCadastroDto dto = new AgendamentoCadastroDto();
        dto.setFkCliente(1);
        dto.setFkFuncionario(2);
        dto.setFkServico(3);
        dto.setFkPacote(4);

        CargoEntity cargo = new CargoEntity();
        cargo.setIdCargo(1);
        cargo.setNome("Esteticista");

        UsuarioEntity funcionario = new UsuarioEntity();
        funcionario.setIdUsuario(2);
        funcionario.setNome("Funcionário Teste");
        funcionario.setEmail("funcionario@teste.com");
        funcionario.setCargo(cargo);

        UsuarioEntity cliente = new UsuarioEntity();
        cliente.setIdUsuario(1);
        cliente.setNome("Cliente Teste");
        cliente.setEmail("cliente@teste.com");

        ServicoEntity servico = new ServicoEntity();
        servico.setIdServico(3);
        servico.setNome("Limpeza de Pele");
        servico.setPreco(150.00);

        PacoteEntity pacote = new PacoteEntity();
        pacote.setIdPacote(4);
        pacote.setNome("Pacote Relaxante");

        AgendamentoEntity agendamento = new AgendamentoEntity();
        agendamento.setIdAgendamento(99);
        agendamento.setCliente(cliente);
        agendamento.setFuncionario(funcionario);
        agendamento.setServico(servico);
        agendamento.setPacote(pacote);

        AgendamentoListagemDto agendamentoListagemDto = new AgendamentoListagemDto();
        agendamentoListagemDto.setIdAgendamento(99);
        // Configure outros campos do DTO conforme necessário

        // Mocks dos repositórios
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(usuarioRepository.findById(2)).thenReturn(Optional.of(funcionario));
        when(servicoRepository.findById(3)).thenReturn(Optional.of(servico));
        when(pacoteRepository.findById(4)).thenReturn(Optional.of(pacote));
        when(agendamentoRepository.save(any(AgendamentoEntity.class))).thenReturn(agendamento);

        // Mock do mapper - assumindo que seja estático
        try (MockedStatic<AgendamentoMapper> mapperMock = mockStatic(AgendamentoMapper.class)) {
            mapperMock.when(() -> AgendamentoMapper.toEntity(eq(dto), eq(cliente), eq(funcionario), eq(servico), eq(pacote)))
                    .thenReturn(agendamento);
            mapperMock.when(() -> AgendamentoMapper.toDto(eq(agendamento)))
                    .thenReturn(agendamentoListagemDto);

            // Act
            AgendamentoListagemDto resultado = agendamentoService.cadastrar(dto);

            // Assert
            assertNotNull(resultado);
            assertEquals(99, resultado.getIdAgendamento());

            // Verificações dos repositórios
            verify(usuarioRepository).findById(1);
            verify(usuarioRepository).findById(2);
            verify(servicoRepository).findById(3);
            verify(pacoteRepository).findById(4);
            verify(agendamentoRepository).save(any(AgendamentoEntity.class));

            // Verificações do mapper
            mapperMock.verify(() -> AgendamentoMapper.toEntity(eq(dto), eq(cliente), eq(funcionario), eq(servico), eq(pacote)));
            mapperMock.verify(() -> AgendamentoMapper.toDto(eq(agendamento)));
        }
    }

    @Test
    void deveCadastrarAgendamentoSemPacote() {
        // Arrange - teste sem pacote
        AgendamentoCadastroDto dto = new AgendamentoCadastroDto();
        dto.setFkCliente(1);
        dto.setFkFuncionario(2);
        dto.setFkServico(3);
        dto.setFkPacote(null); // Sem pacote

        UsuarioEntity cliente = new UsuarioEntity();
        cliente.setIdUsuario(1);
        cliente.setNome("Cliente Teste");

        UsuarioEntity funcionario = new UsuarioEntity();
        funcionario.setIdUsuario(2);
        funcionario.setNome("Funcionário Teste");

        ServicoEntity servico = new ServicoEntity();
        servico.setIdServico(3);

        AgendamentoEntity agendamento = new AgendamentoEntity();
        agendamento.setIdAgendamento(100);

        AgendamentoListagemDto agendamentoListagemDto = new AgendamentoListagemDto();
        agendamentoListagemDto.setIdAgendamento(100);

        // Mocks
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(usuarioRepository.findById(2)).thenReturn(Optional.of(funcionario));
        when(servicoRepository.findById(3)).thenReturn(Optional.of(servico));
        when(agendamentoRepository.save(any(AgendamentoEntity.class))).thenReturn(agendamento);

        try (MockedStatic<AgendamentoMapper> mapperMock = mockStatic(AgendamentoMapper.class)) {
            mapperMock.when(() -> AgendamentoMapper.toEntity(eq(dto), eq(cliente), eq(funcionario), eq(servico), isNull()))
                    .thenReturn(agendamento);
            mapperMock.when(() -> AgendamentoMapper.toDto(eq(agendamento)))
                    .thenReturn(agendamentoListagemDto);

            // Act
            AgendamentoListagemDto resultado = agendamentoService.cadastrar(dto);

            // Assert
            assertNotNull(resultado);
            assertEquals(100, resultado.getIdAgendamento());

            verify(usuarioRepository).findById(1);
            verify(usuarioRepository).findById(2);
            verify(servicoRepository).findById(3);
            verify(pacoteRepository, never()).findById(any());
            verify(agendamentoRepository).save(any(AgendamentoEntity.class));
        }
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        // Arrange
        AgendamentoCadastroDto dto = new AgendamentoCadastroDto();
        dto.setFkCliente(999); // ID inexistente

        when(usuarioRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> agendamentoService.cadastrar(dto)
        );

        assertEquals("Cliente não encontrado", exception.getMessage());
        verify(usuarioRepository).findById(999);
    }

    @Test
    void deveLancarExcecaoQuandoFuncionarioNaoEncontrado() {
        // Arrange
        AgendamentoCadastroDto dto = new AgendamentoCadastroDto();
        dto.setFkCliente(1);
        dto.setFkFuncionario(999); // ID inexistente

        UsuarioEntity cliente = new UsuarioEntity();
        cliente.setIdUsuario(1);

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(usuarioRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> agendamentoService.cadastrar(dto)
        );

        assertEquals("Funcionário não encontrado", exception.getMessage());
        verify(usuarioRepository).findById(1);
        verify(usuarioRepository).findById(999);
    }

    @Test
    void deveLancarExcecaoQuandoServicoNaoEncontrado() {
        // Arrange
        AgendamentoCadastroDto dto = new AgendamentoCadastroDto();
        dto.setFkCliente(1);
        dto.setFkFuncionario(2);
        dto.setFkServico(999); // ID inexistente

        UsuarioEntity cliente = new UsuarioEntity();
        cliente.setIdUsuario(1);

        UsuarioEntity funcionario = new UsuarioEntity();
        funcionario.setIdUsuario(2);

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(usuarioRepository.findById(2)).thenReturn(Optional.of(funcionario));
        when(servicoRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> agendamentoService.cadastrar(dto)
        );

        assertEquals("Serviço não encontrado", exception.getMessage());
        verify(usuarioRepository).findById(1);
        verify(usuarioRepository).findById(2);
        verify(servicoRepository).findById(999);
    }

    @Test
    void deveListarTodosAgendamentosComSucesso() {
        // Arrange
        UsuarioEntity cliente1 = new UsuarioEntity();
        cliente1.setIdUsuario(1);
        cliente1.setNome("Cliente 1");

        UsuarioEntity cliente2 = new UsuarioEntity();
        cliente2.setIdUsuario(2);
        cliente2.setNome("Cliente 2");

        UsuarioEntity funcionario = new UsuarioEntity();
        funcionario.setIdUsuario(3);
        funcionario.setNome("Funcionário Teste");

        ServicoEntity servico1 = new ServicoEntity();
        servico1.setIdServico(1);
        servico1.setNome("Limpeza de Pele");

        ServicoEntity servico2 = new ServicoEntity();
        servico2.setIdServico(2);
        servico2.setNome("Massagem");

        AgendamentoEntity agendamento1 = new AgendamentoEntity();
        agendamento1.setIdAgendamento(1);
        agendamento1.setCliente(cliente1);
        agendamento1.setFuncionario(funcionario);
        agendamento1.setServico(servico1);

        AgendamentoEntity agendamento2 = new AgendamentoEntity();
        agendamento2.setIdAgendamento(2);
        agendamento2.setCliente(cliente2);
        agendamento2.setFuncionario(funcionario);
        agendamento2.setServico(servico2);

        List<AgendamentoEntity> agendamentos = Arrays.asList(agendamento1, agendamento2);

        AgendamentoListagemDto dto1 = new AgendamentoListagemDto(
                1, "Cliente 1", "Funcionário Teste", "Limpeza de Pele",
                LocalDateTime.now(), 150.00, "AGENDADO"
        );

        AgendamentoListagemDto dto2 = new AgendamentoListagemDto(
                2, "Cliente 2", "Funcionário Teste", "Massagem",
                LocalDateTime.now(), 200.00, "AGENDADO"
        );

        // Mock do repositório
        when(agendamentoRepository.findAll()).thenReturn(agendamentos);

        // Mock do mapper
        try (MockedStatic<AgendamentoMapper> mapperMock = mockStatic(AgendamentoMapper.class)) {
            mapperMock.when(() -> AgendamentoMapper.toDto(agendamento1)).thenReturn(dto1);
            mapperMock.when(() -> AgendamentoMapper.toDto(agendamento2)).thenReturn(dto2);

            // Act
            List<AgendamentoListagemDto> resultado = agendamentoService.listarTodos();

            // Assert
            assertNotNull(resultado);
            assertEquals(2, resultado.size());
            assertEquals("Cliente 1", resultado.get(0).getNomeCliente());
            assertEquals("Cliente 2", resultado.get(1).getNomeCliente());
            assertEquals("Limpeza de Pele", resultado.get(0).getNomeServico());
            assertEquals("Massagem", resultado.get(1).getNomeServico());

            // Verify
            verify(agendamentoRepository).findAll();
            mapperMock.verify(() -> AgendamentoMapper.toDto(agendamento1));
            mapperMock.verify(() -> AgendamentoMapper.toDto(agendamento2));
        }
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverAgendamentos() {
        // Arrange
        List<AgendamentoEntity> agendamentosVazio = Collections.emptyList();
        when(agendamentoRepository.findAll()).thenReturn(agendamentosVazio);

        // Act
        List<AgendamentoListagemDto> resultado = agendamentoService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        assertEquals(0, resultado.size());

        // Verify
        verify(agendamentoRepository).findAll();
    }

    @Test
    void deveBuscarAgendamentoPorIdComSucesso() {
        // Arrange
        Integer idBuscado = 1;

        UsuarioEntity cliente = new UsuarioEntity();
        cliente.setIdUsuario(1);
        cliente.setNome("Cliente Teste");

        UsuarioEntity funcionario = new UsuarioEntity();
        funcionario.setIdUsuario(2);
        funcionario.setNome("Funcionário Teste");

        ServicoEntity servico = new ServicoEntity();
        servico.setIdServico(1);
        servico.setNome("Limpeza de Pele");

        AgendamentoEntity agendamento = new AgendamentoEntity();
        agendamento.setIdAgendamento(idBuscado);
        agendamento.setCliente(cliente);
        agendamento.setFuncionario(funcionario);
        agendamento.setServico(servico);
        agendamento.setDtHora(LocalDateTime.of(2024, 12, 15, 14, 30));
        agendamento.setValorPago(150.00);
        agendamento.setStatus("AGENDADO");

        AgendamentoListagemDto agendamentoDto = new AgendamentoListagemDto(
                idBuscado, "Cliente Teste", "Funcionário Teste", "Limpeza de Pele",
                LocalDateTime.of(2024, 12, 15, 14, 30), 150.00, "AGENDADO"
        );

        // Mock do repositório
        when(agendamentoRepository.findById(idBuscado)).thenReturn(Optional.of(agendamento));

        // Mock do mapper
        try (MockedStatic<AgendamentoMapper> mapperMock = mockStatic(AgendamentoMapper.class)) {
            mapperMock.when(() -> AgendamentoMapper.toDto(agendamento)).thenReturn(agendamentoDto);

            // Act
            AgendamentoListagemDto resultado = agendamentoService.buscarPorId(idBuscado);

            // Assert
            assertNotNull(resultado);
            assertEquals(idBuscado, resultado.getIdAgendamento());
            assertEquals("Cliente Teste", resultado.getNomeCliente());
            assertEquals("Funcionário Teste", resultado.getNomeFuncionario());
            assertEquals("Limpeza de Pele", resultado.getNomeServico());
            assertEquals(LocalDateTime.of(2024, 12, 15, 14, 30), resultado.getDtHora());
            assertEquals(150.00, resultado.getValorPago());
            assertEquals("AGENDADO", resultado.getStatus());

            // Verify
            verify(agendamentoRepository).findById(idBuscado);
            mapperMock.verify(() -> AgendamentoMapper.toDto(agendamento));
        }
    }

    @Test
    void deveLancarExcecaoQuandoAgendamentoNaoEncontradoPorId() {
        // Arrange
        Integer idInexistente = 999;
        when(agendamentoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> agendamentoService.buscarPorId(idInexistente)
        );

        assertEquals("Agendamento não encontrado", exception.getMessage());

        // Verify
        verify(agendamentoRepository).findById(idInexistente);
    }

    @Test
    void deveBuscarAgendamentoComPacote() {
        // Arrange - teste com pacote incluído
        Integer idBuscado = 1;

        UsuarioEntity cliente = new UsuarioEntity();
        cliente.setIdUsuario(1);
        cliente.setNome("Cliente VIP");

        UsuarioEntity funcionario = new UsuarioEntity();
        funcionario.setIdUsuario(2);
        funcionario.setNome("Funcionário Especialista");

        ServicoEntity servico = new ServicoEntity();
        servico.setIdServico(1);
        servico.setNome("Tratamento Completo");

        PacoteEntity pacote = new PacoteEntity();
        pacote.setIdPacote(1);
        pacote.setNome("Pacote Premium");

        AgendamentoEntity agendamento = new AgendamentoEntity();
        agendamento.setIdAgendamento(idBuscado);
        agendamento.setCliente(cliente);
        agendamento.setFuncionario(funcionario);
        agendamento.setServico(servico);
        agendamento.setPacote(pacote);
        agendamento.setDtHora(LocalDateTime.of(2024, 12, 20, 16, 0));
        agendamento.setValorPago(500.00);
        agendamento.setStatus("CONFIRMADO");

        AgendamentoListagemDto agendamentoDto = new AgendamentoListagemDto(
                idBuscado, "Cliente VIP", "Funcionário Especialista", "Tratamento Completo",
                LocalDateTime.of(2024, 12, 20, 16, 0), 500.00, "CONFIRMADO"
        );

        // Mock do repositório
        when(agendamentoRepository.findById(idBuscado)).thenReturn(Optional.of(agendamento));

        // Mock do mapper
        try (MockedStatic<AgendamentoMapper> mapperMock = mockStatic(AgendamentoMapper.class)) {
            mapperMock.when(() -> AgendamentoMapper.toDto(agendamento)).thenReturn(agendamentoDto);

            // Act
            AgendamentoListagemDto resultado = agendamentoService.buscarPorId(idBuscado);

            // Assert
            assertNotNull(resultado);
            assertEquals(idBuscado, resultado.getIdAgendamento());
            assertEquals("Cliente VIP", resultado.getNomeCliente());
            assertEquals("Funcionário Especialista", resultado.getNomeFuncionario());
            assertEquals("Tratamento Completo", resultado.getNomeServico());
            assertEquals(500.00, resultado.getValorPago());
            assertEquals("CONFIRMADO", resultado.getStatus());

            // Verify
            verify(agendamentoRepository).findById(idBuscado);
            mapperMock.verify(() -> AgendamentoMapper.toDto(agendamento));
        }
    }

    @Test
    void deveAtualizarAgendamentoComSucesso() {
        // Arrange
        Integer idAgendamento = 1;

        AgendamentoAtualizacaoDto atualizacaoDto = new AgendamentoAtualizacaoDto();
        atualizacaoDto.setDtHora(LocalDateTime.of(2024, 12, 20, 15, 30));
        atualizacaoDto.setStatus("CONFIRMADO");
        atualizacaoDto.setValorPago(200.00);

        UsuarioEntity cliente = new UsuarioEntity();
        cliente.setIdUsuario(1);
        cliente.setNome("Cliente Teste");

        UsuarioEntity funcionario = new UsuarioEntity();
        funcionario.setIdUsuario(2);
        funcionario.setNome("Funcionário Teste");

        ServicoEntity servico = new ServicoEntity();
        servico.setIdServico(1);
        servico.setNome("Limpeza de Pele");

        // Agendamento antes da atualização
        AgendamentoEntity agendamentoOriginal = new AgendamentoEntity();
        agendamentoOriginal.setIdAgendamento(idAgendamento);
        agendamentoOriginal.setCliente(cliente);
        agendamentoOriginal.setFuncionario(funcionario);
        agendamentoOriginal.setServico(servico);
        agendamentoOriginal.setDtHora(LocalDateTime.of(2024, 12, 15, 14, 0));
        agendamentoOriginal.setStatus("AGENDADO");
        agendamentoOriginal.setValorPago(150.00);

        // Agendamento após a atualização (simulado)
        AgendamentoEntity agendamentoAtualizado = new AgendamentoEntity();
        agendamentoAtualizado.setIdAgendamento(idAgendamento);
        agendamentoAtualizado.setCliente(cliente);
        agendamentoAtualizado.setFuncionario(funcionario);
        agendamentoAtualizado.setServico(servico);
        agendamentoAtualizado.setDtHora(LocalDateTime.of(2024, 12, 20, 15, 30));
        agendamentoAtualizado.setStatus("CONFIRMADO");
        agendamentoAtualizado.setValorPago(200.00);

        AgendamentoListagemDto agendamentoListagemDto = new AgendamentoListagemDto(
                idAgendamento, "Cliente Teste", "Funcionário Teste", "Limpeza de Pele",
                LocalDateTime.of(2024, 12, 20, 15, 30), 200.00, "CONFIRMADO"
        );

        // Mocks
        when(agendamentoRepository.findById(idAgendamento)).thenReturn(Optional.of(agendamentoOriginal));
        when(agendamentoRepository.save(agendamentoOriginal)).thenReturn(agendamentoAtualizado);

        try (MockedStatic<AgendamentoMapper> mapperMock = mockStatic(AgendamentoMapper.class)) {
            mapperMock.when(() -> AgendamentoMapper.toDto(agendamentoAtualizado))
                    .thenReturn(agendamentoListagemDto);

            // Act
            AgendamentoListagemDto resultado = agendamentoService.atualizar(idAgendamento, atualizacaoDto);

            // Assert
            assertNotNull(resultado);
            assertEquals(idAgendamento, resultado.getIdAgendamento());
            assertEquals("Cliente Teste", resultado.getNomeCliente());
            assertEquals("Funcionário Teste", resultado.getNomeFuncionario());
            assertEquals("Limpeza de Pele", resultado.getNomeServico());
            assertEquals(LocalDateTime.of(2024, 12, 20, 15, 30), resultado.getDtHora());
            assertEquals(200.00, resultado.getValorPago());
            assertEquals("CONFIRMADO", resultado.getStatus());

            // Verify
            verify(agendamentoRepository).findById(idAgendamento);
            verify(agendamentoRepository).save(agendamentoOriginal);
            mapperMock.verify(() -> AgendamentoMapper.AtualizarEntity(agendamentoOriginal, atualizacaoDto));
            mapperMock.verify(() -> AgendamentoMapper.toDto(agendamentoAtualizado));
        }
    }

    @Test
    void deveLancarExcecaoAoAtualizarAgendamentoInexistente() {
        // Arrange
        Integer idInexistente = 999;
        AgendamentoAtualizacaoDto atualizacaoDto = new AgendamentoAtualizacaoDto();
        atualizacaoDto.setStatus("CONFIRMADO");

        when(agendamentoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> agendamentoService.atualizar(idInexistente, atualizacaoDto)
        );

        assertEquals("Agendamento não encontrado", exception.getMessage());

        // Verify
        verify(agendamentoRepository).findById(idInexistente);
        verify(agendamentoRepository, never()).save(any(AgendamentoEntity.class));
    }

    @Test
    void deveAtualizarApenasDataHora() {
        // Arrange - teste atualizando apenas data/hora
        Integer idAgendamento = 2;

        AgendamentoAtualizacaoDto atualizacaoDto = new AgendamentoAtualizacaoDto();
        atualizacaoDto.setDtHora(LocalDateTime.of(2024, 12, 25, 10, 0));
        // Outros campos ficam null/vazios

        UsuarioEntity cliente = new UsuarioEntity();
        cliente.setIdUsuario(1);
        cliente.setNome("Cliente Premium");

        AgendamentoEntity agendamentoOriginal = new AgendamentoEntity();
        agendamentoOriginal.setIdAgendamento(idAgendamento);
        agendamentoOriginal.setCliente(cliente);
        agendamentoOriginal.setStatus("AGENDADO");

        AgendamentoEntity agendamentoAtualizado = new AgendamentoEntity();
        agendamentoAtualizado.setIdAgendamento(idAgendamento);
        agendamentoAtualizado.setCliente(cliente);
        agendamentoAtualizado.setDtHora(LocalDateTime.of(2024, 12, 25, 10, 0));
        agendamentoAtualizado.setStatus("AGENDADO");

        AgendamentoListagemDto agendamentoDto = new AgendamentoListagemDto(
                idAgendamento, "Cliente Premium", "Funcionário", "Serviço",
                LocalDateTime.of(2024, 12, 25, 10, 0), 0.0, "AGENDADO"
        );

        // Mocks
        when(agendamentoRepository.findById(idAgendamento)).thenReturn(Optional.of(agendamentoOriginal));
        when(agendamentoRepository.save(agendamentoOriginal)).thenReturn(agendamentoAtualizado);

        try (MockedStatic<AgendamentoMapper> mapperMock = mockStatic(AgendamentoMapper.class)) {
            mapperMock.when(() -> AgendamentoMapper.toDto(agendamentoAtualizado))
                    .thenReturn(agendamentoDto);

            // Act
            AgendamentoListagemDto resultado = agendamentoService.atualizar(idAgendamento, atualizacaoDto);

            // Assert
            assertNotNull(resultado);
            assertEquals(LocalDateTime.of(2024, 12, 25, 10, 0), resultado.getDtHora());

            // Verify
            verify(agendamentoRepository).findById(idAgendamento);
            verify(agendamentoRepository).save(agendamentoOriginal);
            mapperMock.verify(() -> AgendamentoMapper.AtualizarEntity(agendamentoOriginal, atualizacaoDto));
        }
    }

    @Test
    void deveDeletarAgendamentoComSucesso() {
        // Arrange
        Integer idAgendamento = 1;
        when(agendamentoRepository.existsById(idAgendamento)).thenReturn(true);

        // Act
        assertDoesNotThrow(() -> agendamentoService.deletar(idAgendamento));

        // Verify
        verify(agendamentoRepository).existsById(idAgendamento);
        verify(agendamentoRepository).deleteById(idAgendamento);
    }

    @Test
    void deveLancarExcecaoAoDeletarAgendamentoInexistente() {
        // Arrange
        Integer idInexistente = 999;
        when(agendamentoRepository.existsById(idInexistente)).thenReturn(false);

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> agendamentoService.deletar(idInexistente)
        );

        assertEquals("Agendamento não encontrado", exception.getMessage());

        // Verify
        verify(agendamentoRepository).existsById(idInexistente);
        verify(agendamentoRepository, never()).deleteById(any(Integer.class));
    }

    @Test
    void deveDeletarAgendamentoExistente() {
        // Arrange - teste com dados mais realistas
        Integer idAgendamento = 5;
        when(agendamentoRepository.existsById(idAgendamento)).thenReturn(true);
        doNothing().when(agendamentoRepository).deleteById(idAgendamento);

        // Act
        agendamentoService.deletar(idAgendamento);

        // Assert - método void, então verificamos apenas as chamadas
        verify(agendamentoRepository).existsById(idAgendamento);
        verify(agendamentoRepository).deleteById(idAgendamento);
    }

    @Test
    void deveVerificarOrdemDasOperacoesNaDelecao() {
        // Arrange - teste para verificar a ordem das operações
        Integer idAgendamento = 10;
        when(agendamentoRepository.existsById(idAgendamento)).thenReturn(true);

        // Usar InOrder para verificar a sequência de chamadas
        InOrder inOrder = inOrder(agendamentoRepository);

        // Act
        agendamentoService.deletar(idAgendamento);

        // Assert - verificar ordem das operações
        inOrder.verify(agendamentoRepository).existsById(idAgendamento);
        inOrder.verify(agendamentoRepository).deleteById(idAgendamento);
    }
}
