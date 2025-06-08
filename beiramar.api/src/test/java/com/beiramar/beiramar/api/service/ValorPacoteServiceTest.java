package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.mapper.ValorPacoteMapper;
import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteCadastroDto;
import com.beiramar.beiramar.api.dto.valorPacoteDtos.ValorPacoteListagemDto;
import com.beiramar.beiramar.api.entity.Pacote;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.entity.ValorPacote;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.PacoteRepository;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
import com.beiramar.beiramar.api.repository.ValorPacoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValorPacoteServiceTest {

    @InjectMocks
    private ValorPacoteService valorPacoteService;

    @Mock
    private ValorPacoteRepository valorPacoteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PacoteRepository pacoteRepository;

    @Test
    void deveCadastrarValorPacoteComSucesso() {
        ValorPacoteCadastroDto dto = new ValorPacoteCadastroDto();
        dto.setFkUsuario(1);
        dto.setFkPacote(2);
        dto.setValorTotal(250.0);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNome("Cliente");

        Pacote pacote = new Pacote(2, "Pacote Dia das Mães", 300.0, 5, 30);

        ValorPacote entity = new ValorPacote();
        entity.setIdValorPacote(10);
        entity.setUsuario(usuario);
        entity.setPacote(pacote);
        entity.setValorTotal(dto.getValorTotal());

        ValorPacoteListagemDto dtoListagem = new ValorPacoteListagemDto(10, 250.0, "Cliente", "Pacote Dia das Mães");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(pacoteRepository.findById(2)).thenReturn(Optional.of(pacote));
        when(valorPacoteRepository.save(any(ValorPacote.class))).thenReturn(entity);

        try (MockedStatic<ValorPacoteMapper> mocked = mockStatic(ValorPacoteMapper.class)) {
            mocked.when(() -> ValorPacoteMapper.toEntity(dto, usuario, pacote)).thenReturn(entity);
            mocked.when(() -> ValorPacoteMapper.toDto(entity)).thenReturn(dtoListagem);

            ValorPacoteListagemDto resultado = valorPacoteService.cadatrar(dto);

            assertNotNull(resultado);
            assertEquals("Cliente", resultado.getNomeUsuario());
            assertEquals("Pacote Dia das Mães", resultado.getNomePacote());
            assertEquals(250.0, resultado.getValorTotal());

            verify(usuarioRepository).findById(1);
            verify(pacoteRepository).findById(2);
            verify(valorPacoteRepository).save(entity);
        }
    }

    @Test
    void deveLancarExcecaoSeUsuarioNaoExistir() {
        ValorPacoteCadastroDto dto = new ValorPacoteCadastroDto();
        dto.setFkUsuario(1);
        dto.setFkPacote(2);

        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> valorPacoteService.cadatrar(dto)
        );

        assertEquals("Cliente não encontrado", ex.getMessage());
    }
    

    @Test
    void deveListarTodosComSucesso() {
        ValorPacote v1 = new ValorPacote();
        v1.setIdValorPacote(1);
        v1.setValorTotal(200.0);

        ValorPacote v2 = new ValorPacote();
        v2.setIdValorPacote(2);
        v2.setValorTotal(300.0);

        ValorPacoteListagemDto dto1 = new ValorPacoteListagemDto(1, 200.0, "Usuário A", "Pacote A");
        ValorPacoteListagemDto dto2 = new ValorPacoteListagemDto(2, 300.0, "Usuário B", "Pacote B");

        when(valorPacoteRepository.findAll()).thenReturn(Arrays.asList(v1, v2));

        try (MockedStatic<ValorPacoteMapper> mocked = mockStatic(ValorPacoteMapper.class)) {
            mocked.when(() -> ValorPacoteMapper.toDto(v1)).thenReturn(dto1);
            mocked.when(() -> ValorPacoteMapper.toDto(v2)).thenReturn(dto2);

            List<ValorPacoteListagemDto> resultado = valorPacoteService.listarTodos();

            assertEquals(2, resultado.size());
            verify(valorPacoteRepository).findAll();
        }
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        ValorPacote valor = new ValorPacote();
        valor.setIdValorPacote(1);
        valor.setValorTotal(150.0);

        ValorPacoteListagemDto dto = new ValorPacoteListagemDto(1, 150.0, "Cliente", "Pacote");

        when(valorPacoteRepository.findById(1)).thenReturn(Optional.of(valor));

        try (MockedStatic<ValorPacoteMapper> mocked = mockStatic(ValorPacoteMapper.class)) {
            mocked.when(() -> ValorPacoteMapper.toDto(valor)).thenReturn(dto);

            ValorPacoteListagemDto resultado = valorPacoteService.buscarPorId(1);

            assertEquals("Cliente", resultado.getNomeUsuario());
            assertEquals("Pacote", resultado.getNomePacote());
            assertEquals(150.0, resultado.getValorTotal());
        }
    }

    @Test
    void deveLancarExcecaoSeValorPacoteNaoForEncontrado() {
        when(valorPacoteRepository.findById(99)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> valorPacoteService.buscarPorId(99)
        );

        assertEquals("Valor Pacote não encontrado", ex.getMessage());
    }

    @Test
    void deveAtualizarValorPacoteComSucesso() {
        ValorPacoteAtualizacaoDto dto = new ValorPacoteAtualizacaoDto();
        dto.setValorTotal(350.0);

        ValorPacote valor = new ValorPacote();
        valor.setIdValorPacote(1);
        valor.setValorTotal(300.0);

        ValorPacote atualizado = new ValorPacote();
        atualizado.setIdValorPacote(1);
        atualizado.setValorTotal(350.0);

        ValorPacoteListagemDto dtoAtualizado = new ValorPacoteListagemDto(1, 350.0, "Cliente", "Pacote");

        when(valorPacoteRepository.findById(1)).thenReturn(Optional.of(valor));
        when(valorPacoteRepository.save(valor)).thenReturn(atualizado);

        try (MockedStatic<ValorPacoteMapper> mocked = mockStatic(ValorPacoteMapper.class)) {
            mocked.when(() -> ValorPacoteMapper.AtualizarEntity(valor, dto)).then(inv -> {
                valor.setValorTotal(dto.getValorTotal());
                return null;
            });
            mocked.when(() -> ValorPacoteMapper.toDto(atualizado)).thenReturn(dtoAtualizado);

            ValorPacoteListagemDto resultado = valorPacoteService.atualizar(1, dto);

            assertEquals(350.0, resultado.getValorTotal());
            verify(valorPacoteRepository).save(valor);
        }
    }

    @Test
    void deveLancarExcecaoAoAtualizarValorInexistente() {
        when(valorPacoteRepository.findById(99)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> valorPacoteService.atualizar(99, new ValorPacoteAtualizacaoDto())
        );

        assertEquals("Valor Pacote não encontrado", ex.getMessage());
    }

    @Test
    void deveDeletarValorPacoteComSucesso() {
        when(valorPacoteRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> valorPacoteService.deletar(1));
        verify(valorPacoteRepository).deleteById(1);
    }

    @Test
    void deveLancarExcecaoAoDeletarValorInexistente() {
        when(valorPacoteRepository.existsById(99)).thenReturn(false);

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> valorPacoteService.deletar(99)
        );

        assertEquals("Valor Pacote não encontrado", ex.getMessage());
        verify(valorPacoteRepository, never()).deleteById(any());
    }
}
