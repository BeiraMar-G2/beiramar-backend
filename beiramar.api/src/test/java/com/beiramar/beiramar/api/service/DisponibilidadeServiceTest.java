package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.disponibilidadeDtos.DisponibilidadeAtualizacaoDto;
import com.beiramar.beiramar.api.dto.disponibilidadeDtos.DisponibilidadeCadastroDto;
import com.beiramar.beiramar.api.dto.disponibilidadeDtos.DisponibilidadeListagemDto;
import com.beiramar.beiramar.api.entity.Disponibilidade;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.DisponibilidadeRepository;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
import com.beiramar.beiramar.api.dto.mapper.DisponibilidadeMapper;
import org.junit.jupiter.api.DisplayName;
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
@DisplayName("Testes do DisponibilidadeService")
class DisponibilidadeServiceTest {

    @InjectMocks
    private DisponibilidadeService disponibilidadeService;

    @Mock
    private DisponibilidadeRepository disponibilidadeRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve cadastrar disponibilidade com sucesso")
    void deveCadastrarDisponibilidadeComSucesso() {
        DisponibilidadeCadastroDto dto = new DisponibilidadeCadastroDto();
        dto.setIdFuncionario(1);
        Usuario usuario = new Usuario();
        Disponibilidade disponibilidadeEntity = new Disponibilidade();
        DisponibilidadeListagemDto disponibilidadeDto = new DisponibilidadeListagemDto();

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(disponibilidadeRepository.save(any(Disponibilidade.class))).thenReturn(disponibilidadeEntity);

        try (MockedStatic<DisponibilidadeMapper> mocked = mockStatic(DisponibilidadeMapper.class)) {
            mocked.when(() -> DisponibilidadeMapper.toEntity(dto, usuario)).thenReturn(disponibilidadeEntity);
            mocked.when(() -> DisponibilidadeMapper.toDto(disponibilidadeEntity)).thenReturn(disponibilidadeDto);

            DisponibilidadeListagemDto resultado = disponibilidadeService.cadastrar(dto);

            assertNotNull(resultado);
            verify(disponibilidadeRepository).save(disponibilidadeEntity);
        }
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar disponibilidade com funcionário inexistente")
    void deveLancarExcecaoAoCadastrarDisponibilidadeComFuncionarioInexistente() {
        DisponibilidadeCadastroDto dto = new DisponibilidadeCadastroDto();
        dto.setIdFuncionario(1);

        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> disponibilidadeService.cadastrar(dto));
    }

    @Test
    @DisplayName("Deve listar todas as disponibilidades com sucesso")
    void deveListarTodasDisponibilidadesComSucesso() {
        Disponibilidade disponibilidade = new Disponibilidade();
        DisponibilidadeListagemDto dto = new DisponibilidadeListagemDto();

        when(disponibilidadeRepository.findAll()).thenReturn(List.of(disponibilidade));

        try (MockedStatic<DisponibilidadeMapper> mocked = mockStatic(DisponibilidadeMapper.class)) {
            mocked.when(() -> DisponibilidadeMapper.toDto(disponibilidade)).thenReturn(dto);

            List<DisponibilidadeListagemDto> resultado = disponibilidadeService.listarTodos();

            assertEquals(1, resultado.size());
            verify(disponibilidadeRepository).findAll();
        }
    }

    @Test
    @DisplayName("Deve buscar disponibilidade por ID com sucesso")
    void deveBuscarDisponibilidadePorIdComSucesso() {
        Disponibilidade disponibilidade = new Disponibilidade();
        DisponibilidadeListagemDto dto = new DisponibilidadeListagemDto();

        when(disponibilidadeRepository.findById(1)).thenReturn(Optional.of(disponibilidade));

        try (MockedStatic<DisponibilidadeMapper> mocked = mockStatic(DisponibilidadeMapper.class)) {
            mocked.when(() -> DisponibilidadeMapper.toDto(disponibilidade)).thenReturn(dto);

            DisponibilidadeListagemDto resultado = disponibilidadeService.buscarPorId(1);

            assertNotNull(resultado);
            verify(disponibilidadeRepository).findById(1);
        }
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar disponibilidade inexistente por ID")
    void deveLancarExcecaoAoBuscarDisponibilidadeInexistente() {
        when(disponibilidadeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> disponibilidadeService.buscarPorId(1));
    }

    @Test
    @DisplayName("Deve atualizar disponibilidade com sucesso")
    void deveAtualizarDisponibilidadeComSucesso() {
        Disponibilidade disponibilidade = new Disponibilidade();
        DisponibilidadeAtualizacaoDto dto = new DisponibilidadeAtualizacaoDto();
        DisponibilidadeListagemDto dtoAtualizado = new DisponibilidadeListagemDto();

        when(disponibilidadeRepository.findById(1)).thenReturn(Optional.of(disponibilidade));
        when(disponibilidadeRepository.save(disponibilidade)).thenReturn(disponibilidade);

        try (MockedStatic<DisponibilidadeMapper> mocked = mockStatic(DisponibilidadeMapper.class)) {
            mocked.when(() -> DisponibilidadeMapper.AtualizarEntity(disponibilidade, dto)).thenAnswer(inv -> {
                disponibilidade.setHoraInicio(dto.getHoraInicio());
                disponibilidade.setHoraFim(dto.getHoraFim());
                return null;
            });

            mocked.when(() -> DisponibilidadeMapper.toDto(disponibilidade)).thenReturn(dtoAtualizado);

            DisponibilidadeListagemDto resultado = disponibilidadeService.atualizar(1, dto);

            assertNotNull(resultado);
            verify(disponibilidadeRepository).save(disponibilidade);
        }
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar disponibilidade inexistente")
    void deveLancarExcecaoAoAtualizarDisponibilidadeInexistente() {
        DisponibilidadeAtualizacaoDto dto = new DisponibilidadeAtualizacaoDto();

        when(disponibilidadeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> disponibilidadeService.atualizar(1, dto));
    }

    @Test
    @DisplayName("Deve deletar disponibilidade com sucesso")
    void deveDeletarDisponibilidadeComSucesso() {
        when(disponibilidadeRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> disponibilidadeService.deletar(1));
        verify(disponibilidadeRepository).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar disponibilidade inexistente")
    void deveLancarExcecaoAoDeletarDisponibilidadeInexistente() {
        when(disponibilidadeRepository.existsById(1)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> disponibilidadeService.deletar(1));
        verify(disponibilidadeRepository, never()).deleteById(any());
    }
}

