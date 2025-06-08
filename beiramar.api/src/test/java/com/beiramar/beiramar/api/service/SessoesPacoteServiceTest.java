package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.mapper.SessoesPacoteMapper;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteCadastroDto;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteListagemDto;
import com.beiramar.beiramar.api.entity.Pacote;
import com.beiramar.beiramar.api.entity.Servico;
import com.beiramar.beiramar.api.entity.SessoesPacote;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.PacoteRepository;
import com.beiramar.beiramar.api.repository.ServicoRepository;
import com.beiramar.beiramar.api.repository.SessoesPacoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessoesPacoteServiceTest {

    @InjectMocks
    private SessoesPacoteService service;

    @Mock
    private SessoesPacoteRepository sessoesPacoteRepository;

    @Mock
    private PacoteRepository pacoteRepository;

    @Mock
    private ServicoRepository servicoRepository;

    @Test
    void deveCadastrarComSucesso() {
        SessoesPacoteCadastroDto dto = new SessoesPacoteCadastroDto();
        dto.setFkPacote(1);
        dto.setFkServico(2);
        dto.setQtdSessoes(5);

        Pacote pacote = new Pacote();
        pacote.setIdPacote(1);
        pacote.setNome("Pacote Top");

        Servico servico = new Servico(2, "Massagem", 45, 80.0, "Relaxante");

        SessoesPacote sessoesPacote = new SessoesPacote();
        sessoesPacote.setIdSessoesPacote(10);
        sessoesPacote.setPacote(pacote);
        sessoesPacote.setServico(servico);
        sessoesPacote.setQtdSessoes(5);

        SessoesPacoteListagemDto dtoEsperado = new SessoesPacoteListagemDto(10, "Pacote Top", "Massagem", 5);

        when(pacoteRepository.findById(1)).thenReturn(Optional.of(pacote));
        when(servicoRepository.findById(2)).thenReturn(Optional.of(servico));
        when(sessoesPacoteRepository.save(any(SessoesPacote.class))).thenReturn(sessoesPacote);

        try (MockedStatic<SessoesPacoteMapper> mocked = mockStatic(SessoesPacoteMapper.class)) {
            when(sessoesPacoteRepository.save(any())).thenReturn(sessoesPacote);

            SessoesPacoteListagemDto resultado = service.cadastrar(dto);

            assertNotNull(resultado);
            assertEquals("Pacote Top", resultado.getNomePacote());

            verify(pacoteRepository).findById(1);
            verify(servicoRepository).findById(2);
            verify(sessoesPacoteRepository).save(any(SessoesPacote.class));
            mocked.verify(() -> SessoesPacoteMapper.toDto(sessoesPacote));
        }
    }

    @Test
    void deveLancarExcecaoSeServicoNaoExistir() {
        SessoesPacoteCadastroDto dto = new SessoesPacoteCadastroDto();
        dto.setFkServico(999);
        dto.setFkPacote(1);

        when(servicoRepository.findById(999)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.cadastrar(dto)
        );

        assertEquals("Serviço não encontrado", ex.getMessage());
        verify(servicoRepository).findById(999);
        verify(pacoteRepository, never()).findById(any());
    }

    @Test
    void deveLancarExcecaoSePacoteNaoExistir() {
        SessoesPacoteCadastroDto dto = new SessoesPacoteCadastroDto();
        dto.setFkServico(1);
        dto.setFkPacote(999);

        Servico servico = new Servico(1, "Limpeza", 60, 100.0, "Facial");
        when(servicoRepository.findById(1)).thenReturn(Optional.of(servico));
        when(pacoteRepository.findById(999)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.cadastrar(dto)
        );

        assertEquals("Pacote não encontrado", ex.getMessage());
        verify(servicoRepository).findById(1);
        verify(pacoteRepository).findById(999);
    }

    @Test
    void deveListarTodosComSucesso() {
        Pacote pacote = new Pacote();
        pacote.setNome("Pacote A");

        Servico servico = new Servico(1, "Massagem", 30, 50.0, "Relaxante");

        SessoesPacote sessao = new SessoesPacote();
        sessao.setIdSessoesPacote(1);
        sessao.setPacote(pacote);
        sessao.setServico(servico);
        sessao.setQtdSessoes(3);

        SessoesPacoteListagemDto dto = new SessoesPacoteListagemDto(1, "Pacote A", "Massagem", 3);

        when(sessoesPacoteRepository.findAll()).thenReturn(List.of(sessao));

        try (MockedStatic<SessoesPacoteMapper> mocked = mockStatic(SessoesPacoteMapper.class)) {
            mocked.when(() -> SessoesPacoteMapper.toDto(sessao)).thenReturn(dto);

            List<SessoesPacoteListagemDto> resultado = service.listarTodos();

            assertEquals(1, resultado.size());
            assertEquals("Pacote A", resultado.get(0).getNomePacote());
            verify(sessoesPacoteRepository).findAll();
            mocked.verify(() -> SessoesPacoteMapper.toDto(sessao));
        }
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        SessoesPacote sessao = new SessoesPacote();
        sessao.setIdSessoesPacote(1);

        SessoesPacoteListagemDto dto = new SessoesPacoteListagemDto(1, "Pacote", "Servico", 2);

        when(sessoesPacoteRepository.findById(1)).thenReturn(Optional.of(sessao));

        try (MockedStatic<SessoesPacoteMapper> mocked = mockStatic(SessoesPacoteMapper.class)) {
            mocked.when(() -> SessoesPacoteMapper.toDto(sessao)).thenReturn(dto);

            SessoesPacoteListagemDto resultado = service.buscarPorId(1);

            assertEquals(1, resultado.getIdSessaoPacote());
            verify(sessoesPacoteRepository).findById(1);
            mocked.verify(() -> SessoesPacoteMapper.toDto(sessao));
        }
    }

    @Test
    void deveLancarExcecaoAoBuscarIdInexistente() {
        when(sessoesPacoteRepository.findById(999)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.buscarPorId(999)
        );

        assertEquals("Sessão Pacote não encontrado", ex.getMessage());
    }

    @Test
    void deveAtualizarComSucesso() {
        SessoesPacoteAtualizacaoDto dto = new SessoesPacoteAtualizacaoDto();
        dto.setQtdSessoes(7);

        SessoesPacote existente = new SessoesPacote();
        existente.setIdSessoesPacote(1);

        SessoesPacoteListagemDto dtoRetorno = new SessoesPacoteListagemDto(1, "Pacote", "Servico", 7);

        when(sessoesPacoteRepository.findById(1)).thenReturn(Optional.of(existente));
        when(sessoesPacoteRepository.save(existente)).thenReturn(existente);

        try (MockedStatic<SessoesPacoteMapper> mocked = mockStatic(SessoesPacoteMapper.class)) {
            mocked.when(() -> SessoesPacoteMapper.toDto(existente)).thenReturn(dtoRetorno);

            SessoesPacoteListagemDto resultado = service.atualizar(1, dto);

            assertEquals(7, resultado.getQtdSessoes());
            verify(sessoesPacoteRepository).save(existente);
            mocked.verify(() -> SessoesPacoteMapper.toDto(existente));
        }
    }

    @Test
    void deveLancarExcecaoAoAtualizarInexistente() {
        SessoesPacoteAtualizacaoDto dto = new SessoesPacoteAtualizacaoDto();
        dto.setQtdSessoes(4);

        when(sessoesPacoteRepository.findById(999)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.atualizar(999, dto)
        );

        assertEquals("Sessão Pacote não encontrado", ex.getMessage());
    }

    @Test
    void deveDeletarComSucesso() {
        when(sessoesPacoteRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> service.deletar(1));

        verify(sessoesPacoteRepository).existsById(1);
        verify(sessoesPacoteRepository).deleteById(1);
    }

    @Test
    void deveLancarExcecaoAoDeletarInexistente() {
        when(sessoesPacoteRepository.existsById(999)).thenReturn(false);

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.deletar(999)
        );

        assertEquals("Sessão pacote não encontrado", ex.getMessage());
    }
}
