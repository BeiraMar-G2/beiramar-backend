package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.mapper.SessoesPacoteMapper;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteCadastroDto;
import com.beiramar.beiramar.api.dto.sessaoPacoteDtos.SessoesPacoteListagemDto;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.sessoespacotepersistence.SessoesPacoteEntity;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.infrastructure.persistence.pacotepersistence.PacoteJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.sessoespacotepersistence.SessoesPacoteJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessoesPacoteServiceTest {

    @InjectMocks
    private SessoesPacoteService service;

    @Mock
    private SessoesPacoteJpaRepository sessoesPacoteRepository;

    @Mock
    private PacoteJpaRepository pacoteRepository;

    @Mock
    private ServicoJpaRepository servicoRepository;

    @Test
    void deveCadastrarComSucesso() {
        SessoesPacoteCadastroDto dto = new SessoesPacoteCadastroDto();
        dto.setFkPacote(1);
        dto.setFkServico(2);
        dto.setQtdSessoes(5);

        PacoteEntity pacote = new PacoteEntity();
        pacote.setIdPacote(1);
        pacote.setNome("Pacote Top");

        ServicoEntity servico = new ServicoEntity(2, "Massagem", 45, 80.0, "Relaxante");

        SessoesPacoteEntity sessoesPacote = new SessoesPacoteEntity();
        sessoesPacote.setIdSessoesPacote(10);
        sessoesPacote.setPacote(pacote);
        sessoesPacote.setServico(servico);
        sessoesPacote.setQtdSessoes(5);

        SessoesPacoteListagemDto dtoEsperado = new SessoesPacoteListagemDto(10, "Pacote Top", "Massagem", 5);

        when(pacoteRepository.findById(1)).thenReturn(Optional.of(pacote));
        when(servicoRepository.findById(2)).thenReturn(Optional.of(servico));
        when(sessoesPacoteRepository.save(any(SessoesPacoteEntity.class))).thenReturn(sessoesPacote);

        try (MockedStatic<SessoesPacoteMapper> mocked = mockStatic(SessoesPacoteMapper.class)) {
            when(sessoesPacoteRepository.save(any())).thenReturn(sessoesPacote);

            SessoesPacoteListagemDto resultado = service.cadastrar(dto);

            assertNotNull(resultado);
            assertEquals("Pacote Top", resultado.getNomePacote());

            verify(pacoteRepository).findById(1);
            verify(servicoRepository).findById(2);
            verify(sessoesPacoteRepository).save(any(SessoesPacoteEntity.class));
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

        ServicoEntity servico = new ServicoEntity(1, "Limpeza", 60, 100.0, "Facial");
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
        PacoteEntity pacote = new PacoteEntity();
        pacote.setNome("Pacote A");

        ServicoEntity servico = new ServicoEntity(1, "Massagem", 30, 50.0, "Relaxante");

        SessoesPacoteEntity sessao = new SessoesPacoteEntity();
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
        SessoesPacoteEntity sessao = new SessoesPacoteEntity();
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

        SessoesPacoteEntity existente = new SessoesPacoteEntity();
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
