package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.mapper.PacoteMapper;
import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteCadastroDto;
import com.beiramar.beiramar.api.dto.pacoteDtos.PacoteListagemDto;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacoteServiceTest {

    @InjectMocks
    private PacoteService pacoteService;

    @Mock
    private PacoteJpaRepository pacoteRepository;

    @Test
    void deveCadastrarPacoteComSucesso() {
        PacoteCadastroDto dto = new PacoteCadastroDto();
        dto.setNome("Pacote Relax");
        dto.setPrecoTotalSemDesconto(300.0);
        dto.setQtdSessoesTotal(5);
        dto.setTempoLimiteDias(30);

        PacoteEntity pacoteEntity = new PacoteEntity(1, "Pacote Relax", 300.0, 5, 30);
        PacoteListagemDto pacoteDto = new PacoteListagemDto(1, "Pacote Relax", 300.0, 5, 30);

        when(pacoteRepository.save(any(PacoteEntity.class))).thenReturn(pacoteEntity);

        try (MockedStatic<PacoteMapper> mocked = mockStatic(PacoteMapper.class)) {
            mocked.when(() -> PacoteMapper.toEntity(dto)).thenReturn(pacoteEntity);
            mocked.when(() -> PacoteMapper.toDto(pacoteEntity)).thenReturn(pacoteDto);

            PacoteListagemDto resultado = pacoteService.cadastrar(dto);

            assertNotNull(resultado);
            assertEquals("Pacote Relax", resultado.getNome());
            verify(pacoteRepository).save(pacoteEntity);
            mocked.verify(() -> PacoteMapper.toEntity(dto));
            mocked.verify(() -> PacoteMapper.toDto(pacoteEntity));
        }
    }

    @Test
    void deveListarTodosPacotesComSucesso() {
        PacoteEntity pacote1 = new PacoteEntity(1, "Pacote Bronze", 100.0, 3, 15);
        PacoteEntity pacote2 = new PacoteEntity(2, "Pacote Ouro", 500.0, 10, 60);

        when(pacoteRepository.findAll()).thenReturn(Arrays.asList(pacote1, pacote2));

        PacoteListagemDto dto1 = new PacoteListagemDto(1, "Pacote Bronze", 100.0, 3, 15);
        PacoteListagemDto dto2 = new PacoteListagemDto(2, "Pacote Ouro", 500.0, 10, 60);

        try (MockedStatic<PacoteMapper> mocked = mockStatic(PacoteMapper.class)) {
            mocked.when(() -> PacoteMapper.toDto(pacote1)).thenReturn(dto1);
            mocked.when(() -> PacoteMapper.toDto(pacote2)).thenReturn(dto2);

            List<PacoteListagemDto> resultado = pacoteService.listarTodos();

            assertEquals(2, resultado.size());
            verify(pacoteRepository).findAll();
        }
    }

    @Test
    void deveBuscarPacotePorIdComSucesso() {
        PacoteEntity pacote = new PacoteEntity(1, "Pacote Básico", 150.0, 4, 20);
        PacoteListagemDto dto = new PacoteListagemDto(1, "Pacote Básico", 150.0, 4, 20);

        when(pacoteRepository.findById(1)).thenReturn(Optional.of(pacote));

        try (MockedStatic<PacoteMapper> mocked = mockStatic(PacoteMapper.class)) {
            mocked.when(() -> PacoteMapper.toDto(pacote)).thenReturn(dto);

            PacoteListagemDto resultado = pacoteService.buscarPorId(1);

            assertEquals("Pacote Básico", resultado.getNome());
            verify(pacoteRepository).findById(1);
        }
    }

    @Test
    void deveLancarExcecaoAoBuscarPacoteInexistente() {
        when(pacoteRepository.findById(999)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> pacoteService.buscarPorId(999)
        );

        assertEquals("Pacote não encontrado", ex.getMessage());
        verify(pacoteRepository).findById(999);
    }

    @Test
    void deveAtualizarPacoteComSucesso() {
        PacoteAtualizacaoDto dto = new PacoteAtualizacaoDto();
        dto.setNome("Pacote Atualizado");
        dto.setPrecoTotalSemDesconto(250.0);
        dto.setQtdSessoesTotal(6);
        dto.setTempoLimiteDias(40);

        PacoteEntity existente = new PacoteEntity(1, "Pacote Antigo", 200.0, 5, 30);
        PacoteEntity atualizado = new PacoteEntity(1, "Pacote Atualizado", 250.0, 6, 40);
        PacoteListagemDto dtoAtualizado = new PacoteListagemDto(1, "Pacote Atualizado", 250.0, 6, 40);

        when(pacoteRepository.findById(1)).thenReturn(Optional.of(existente));
        when(pacoteRepository.save(existente)).thenReturn(atualizado);

        try (MockedStatic<PacoteMapper> mocked = mockStatic(PacoteMapper.class)) {
            mocked.when(() -> PacoteMapper.AtualizarEntity(existente, dto)).thenAnswer(inv -> {
                existente.setNome(dto.getNome());
                existente.setPrecoTotalSemDesconto(dto.getPrecoTotalSemDesconto());
                existente.setQtdSessoesTotal(dto.getQtdSessoesTotal());
                existente.setTempoLimiteDias(dto.getTempoLimiteDias());
                return null;
            });

            mocked.when(() -> PacoteMapper.toDto(atualizado)).thenReturn(dtoAtualizado);

            PacoteListagemDto resultado = pacoteService.atualizar(1, dto);

            assertEquals("Pacote Atualizado", resultado.getNome());
            assertEquals(250.0, resultado.getPrecoTotalSemDesconto());
            verify(pacoteRepository).findById(1);
            verify(pacoteRepository).save(existente);
        }
    }

    @Test
    void deveLancarExcecaoAoAtualizarPacoteInexistente() {
        when(pacoteRepository.findById(999)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> pacoteService.atualizar(999, new PacoteAtualizacaoDto())
        );

        assertEquals("Pacote não encontrado", ex.getMessage());
    }

    @Test
    void deveDeletarPacoteComSucesso() {
        when(pacoteRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> pacoteService.deletar(1));
        verify(pacoteRepository).deleteById(1);
    }

    @Test
    void deveLancarExcecaoAoDeletarPacoteInexistente() {
        when(pacoteRepository.existsById(999)).thenReturn(false);

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> pacoteService.deletar(999)
        );

        assertEquals("Pacote não encontrado", ex.getMessage());
        verify(pacoteRepository, never()).deleteById(any());
    }
}
