package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.infrastructure.features.dto.servicoDtos.ServicoCadastroDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.servicoDtos.ServicoListagemDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.mapper.ServicoMapper;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoEntity;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.infrastructure.persistence.servicopersistence.ServicoJpaRepository;
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

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ServicoServiceTest {

    @InjectMocks
    private ServicoService servicoService;

    @Mock
    private ServicoJpaRepository servicoRepository;

    @Test
    void deveCadastrarServicoComSucesso() {
        ServicoCadastroDto dto = new ServicoCadastroDto();
        dto.setNome("Limpeza de Pele");
        dto.setPreco(100.0);
        dto.setDescricao("Descrição...");
        dto.setDuracao(60);

        ServicoEntity servicoEntity = new ServicoEntity();
        servicoEntity.setIdServico(1);
        servicoEntity.setNome(dto.getNome());
        servicoEntity.setPreco(dto.getPreco());
        servicoEntity.setDescricao(dto.getDescricao());
        servicoEntity.setDuracao(dto.getDuracao());

        ServicoListagemDto servicoDto = new ServicoListagemDto(
                1, "Limpeza de Pele", 100.0, "Descrição...", 60
        );

        when(servicoRepository.save(any(ServicoEntity.class))).thenReturn(servicoEntity);

        try (MockedStatic<ServicoMapper> mocked = mockStatic(ServicoMapper.class)) {
            mocked.when(() -> ServicoMapper.toEntity(dto)).thenReturn(servicoEntity);
            mocked.when(() -> ServicoMapper.toDto(servicoEntity)).thenReturn(servicoDto);

            ServicoListagemDto resultado = servicoService.cadastrarServico(dto);

            assertNotNull(resultado);
            assertEquals("Limpeza de Pele", resultado.getNome());
            assertEquals(100.0, resultado.getPreco());
            assertEquals("Descrição...", resultado.getDescricao());
            assertEquals(60, resultado.getDuracao());

            verify(servicoRepository).save(servicoEntity);
            mocked.verify(() -> ServicoMapper.toEntity(dto));
            mocked.verify(() -> ServicoMapper.toDto(servicoEntity));
        }
    }

    @Test
    void deveListarTodosServicosComSucesso() {
        ServicoEntity s1 = new ServicoEntity(1, "Massagem", 45, 80.0, "Relaxante");
        ServicoEntity s2 = new ServicoEntity(2, "Limpeza", 60, 100.0, "Facial");

        when(servicoRepository.findAll()).thenReturn(Arrays.asList(s1, s2));

        ServicoListagemDto dto1 = new ServicoListagemDto(1, "Massagem", 80.0, "Relaxante", 45);
        ServicoListagemDto dto2 = new ServicoListagemDto(2, "Limpeza", 100.0, "Facial", 60);

        try (MockedStatic<ServicoMapper> mocked = mockStatic(ServicoMapper.class)) {
            mocked.when(() -> ServicoMapper.toDto(s1)).thenReturn(dto1);
            mocked.when(() -> ServicoMapper.toDto(s2)).thenReturn(dto2);

            List<ServicoListagemDto> resultado = servicoService.listarTodosServicos();

            assertEquals(2, resultado.size());
            assertEquals("Massagem", resultado.get(0).getNome());
            assertEquals("Limpeza", resultado.get(1).getNome());

            verify(servicoRepository).findAll();
        }
    }

    @Test
    void deveLancarExcecaoAoListarQuandoNaoHouverServicos() {
        when(servicoRepository.findAll()).thenReturn(Collections.emptyList());

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> servicoService.listarTodosServicos()
        );

        assertEquals("Nenhum servico encontrado", ex.getMessage());
        verify(servicoRepository).findAll();
    }

    @Test
    void deveBuscarServicoPorIdComSucesso() {
        ServicoEntity servico = new ServicoEntity(1, "Drenagem", 50, 90.0, "Descrição");
        ServicoListagemDto dto = new ServicoListagemDto(1, "Drenagem", 90.0, "Descrição", 50);

        when(servicoRepository.findById(1)).thenReturn(Optional.of(servico));

        try (MockedStatic<ServicoMapper> mocked = mockStatic(ServicoMapper.class)) {
            mocked.when(() -> ServicoMapper.toDto(servico)).thenReturn(dto);

            ServicoListagemDto resultado = servicoService.buscarServicoPorId(1);

            assertEquals("Drenagem", resultado.getNome());
            assertEquals("Descrição", resultado.getDescricao());
        }
    }

    @Test
    void deveLancarExcecaoQuandoServicoNaoEncontradoPorId() {
        when(servicoRepository.findById(999)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> servicoService.buscarServicoPorId(999)
        );

        assertEquals("Serviço não encontrado", ex.getMessage());
        verify(servicoRepository).findById(999);
    }

    @Test
    void deveAtualizarServicoComSucesso() {
        ServicoCadastroDto dto = new ServicoCadastroDto();
        dto.setNome("Novo Nome");
        dto.setPreco(120.0);
        dto.setDescricao("Nova descrição");
        dto.setDuracao(70);

        ServicoEntity existente = new ServicoEntity(1, "Antigo", 60, 100.0, "Antiga desc");
        ServicoEntity atualizado = new ServicoEntity(1, "Novo Nome", 70, 120.0, "Nova descrição");
        ServicoListagemDto dtoAtualizado = new ServicoListagemDto(1, "Novo Nome", 120.0, "Nova descrição", 70);

        when(servicoRepository.findById(1)).thenReturn(Optional.of(existente));
        when(servicoRepository.save(existente)).thenReturn(atualizado);

        try (MockedStatic<ServicoMapper> mocked = mockStatic(ServicoMapper.class)) {
            mocked.when(() -> ServicoMapper.toDto(atualizado)).thenReturn(dtoAtualizado);

            ServicoListagemDto resultado = servicoService.atualizarServico(1, dto);

            assertEquals("Novo Nome", resultado.getNome());
            assertEquals("Nova descrição", resultado.getDescricao());
            assertEquals(120.0, resultado.getPreco());
            assertEquals(70, resultado.getDuracao());
        }
    }

    @Test
    void deveLancarExcecaoAoAtualizarServicoInexistente() {
        ServicoCadastroDto dto = new ServicoCadastroDto();
        when(servicoRepository.findById(99)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> servicoService.atualizarServico(99, dto)
        );

        assertEquals("Serviço não encontrado", ex.getMessage());
    }

    @Test
    void deveDeletarServicoComSucesso() {
        when(servicoRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> servicoService.deletarServico(1));
        verify(servicoRepository).deleteById(1);
    }

    @Test
    void deveLancarExcecaoAoDeletarServicoInexistente() {
        when(servicoRepository.existsById(999)).thenReturn(false);

        EntidadeNaoEncontradaException ex = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> servicoService.deletarServico(999)
        );

        assertEquals("Serviço não encontrado", ex.getMessage());
        verify(servicoRepository, never()).deleteById(any());
    }
}
