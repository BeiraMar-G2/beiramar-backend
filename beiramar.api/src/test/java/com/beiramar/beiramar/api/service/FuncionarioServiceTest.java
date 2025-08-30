package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.funcionarioDtos.FuncionarioAtualizacaoDto;
import com.beiramar.beiramar.api.dto.funcionarioDtos.FuncionarioCadastroDto;
import com.beiramar.beiramar.api.dto.funcionarioDtos.FuncionarioListagemDto;
import com.beiramar.beiramar.api.dto.mapper.FuncionarioMapper;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.core.application.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do FuncionarioService")
class FuncionarioServiceTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private UsuarioJpaRepository usuarioRepository;

    @Mock
    private CargoJpaRepository cargoRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Deve cadastrar funcionário com sucesso")
    void deveCadastrarFuncionarioComSucesso() {
        FuncionarioCadastroDto dto = new FuncionarioCadastroDto();
        dto.setSenha("senha123");

        CargoEntity cargoFuncionario = new CargoEntity();
        UsuarioEntity funcionarioEntity = new UsuarioEntity();
        UsuarioEntity usuarioSalvo = new UsuarioEntity();
        FuncionarioListagemDto funcionarioDto = new FuncionarioListagemDto();

        when(cargoRepository.findByNomeIgnoreCase("FUNCIONARIO")).thenReturn(Optional.of(cargoFuncionario));


        when(passwordEncoder.encode(anyString())).thenReturn("senhaCriptografada");
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioSalvo);

        try (MockedStatic<FuncionarioMapper> mocked = mockStatic(FuncionarioMapper.class)) {
            mocked.when(() -> FuncionarioMapper.toEntity(dto, cargoFuncionario)).thenReturn(funcionarioEntity);
            mocked.when(() -> FuncionarioMapper.toDto(usuarioSalvo)).thenReturn(funcionarioDto);

            FuncionarioListagemDto resultado = funcionarioService.cadastrarFuncionario(dto);

            assertNotNull(resultado);
            verify(usuarioRepository).save(funcionarioEntity);
            mocked.verify(() -> FuncionarioMapper.toEntity(dto, cargoFuncionario));
            mocked.verify(() -> FuncionarioMapper.toDto(usuarioSalvo));
        }
    }


    @Test
    @DisplayName("Deve lançar exceção ao cadastrar funcionário sem cargo FUNCIONARIO")
    void deveLancarExcecaoAoCadastrarFuncionarioSemCargoFuncionario() {
        FuncionarioCadastroDto dto = new FuncionarioCadastroDto();
        dto.setSenha("senha123");

        when(cargoRepository.findByNomeIgnoreCase("FUNCIONARIO")).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> funcionarioService.cadastrarFuncionario(dto));
    }

    @Test
    @DisplayName("Deve listar todos os funcionários com sucesso")
    void deveListarTodosFuncionariosComSucesso() {
        CargoEntity cargoFuncionario = new CargoEntity();
        UsuarioEntity usuario1 = new UsuarioEntity();
        UsuarioEntity usuario2 = new UsuarioEntity();
        FuncionarioListagemDto dto1 = new FuncionarioListagemDto();
        FuncionarioListagemDto dto2 = new FuncionarioListagemDto();

        when(cargoRepository.findByNomeIgnoreCase("FUNCIONARIO")).thenReturn(Optional.of(cargoFuncionario));
        when(usuarioRepository.findByCargo(cargoFuncionario)).thenReturn(List.of(usuario1, usuario2));

        try (MockedStatic<FuncionarioMapper> mocked = mockStatic(FuncionarioMapper.class)) {
            mocked.when(() -> FuncionarioMapper.toDto(usuario1)).thenReturn(dto1);
            mocked.when(() -> FuncionarioMapper.toDto(usuario2)).thenReturn(dto2);

            List<FuncionarioListagemDto> resultado = funcionarioService.listarTodosFuncionarios();

            assertEquals(2, resultado.size());
            verify(usuarioRepository).findByCargo(cargoFuncionario);
        }
    }

    @Test
    @DisplayName("Deve lançar exceção ao listar funcionários sem cargo FUNCIONARIO")
    void deveLancarExcecaoAoListarFuncionariosSemCargoFuncionario() {
        when(cargoRepository.findByNomeIgnoreCase("FUNCIONARIO")).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> funcionarioService.listarTodosFuncionarios());
    }

    @Test
    @DisplayName("Deve buscar funcionário por ID com sucesso")
    void deveBuscarFuncionarioPorIdComSucesso() {
        UsuarioEntity usuario = new UsuarioEntity();
        FuncionarioListagemDto funcionarioDto = new FuncionarioListagemDto();

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        try (MockedStatic<FuncionarioMapper> mocked = mockStatic(FuncionarioMapper.class)) {
            mocked.when(() -> FuncionarioMapper.toDto(usuario)).thenReturn(funcionarioDto);

            FuncionarioListagemDto resultado = funcionarioService.buscarPorId(1);

            assertNotNull(resultado);
            verify(usuarioRepository).findById(1);
        }
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar funcionário por ID inexistente")
    void deveLancarExcecaoAoBuscarFuncionarioPorIdInexistente() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> funcionarioService.buscarPorId(1));
    }

    @Test
    @DisplayName("Deve atualizar funcionário com sucesso")
    void deveAtualizarFuncionarioComSucesso() {
        UsuarioEntity usuario = new UsuarioEntity();
        FuncionarioAtualizacaoDto dto = new FuncionarioAtualizacaoDto();
        FuncionarioListagemDto dtoAtualizado = new FuncionarioListagemDto();

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        try (MockedStatic<FuncionarioMapper> mocked = mockStatic(FuncionarioMapper.class)) {
            mocked.when(() -> FuncionarioMapper.atualizarEntity(usuario, dto)).thenAnswer(inv -> {
                usuario.setNome(dto.getNome());
                usuario.setEmail(dto.getEmail());
                return null;
            });

            mocked.when(() -> FuncionarioMapper.toDto(usuario)).thenReturn(dtoAtualizado);

            FuncionarioListagemDto resultado = funcionarioService.atualizarFuncionario(1, dto);

            assertNotNull(resultado);
            verify(usuarioRepository).save(usuario);
        }
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar funcionário inexistente")
    void deveLancarExcecaoAoAtualizarFuncionarioInexistente() {
        FuncionarioAtualizacaoDto dto = new FuncionarioAtualizacaoDto();

        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> funcionarioService.atualizarFuncionario(1, dto));
    }

    @Test
    @DisplayName("Deve deletar funcionário com sucesso")
    void deveDeletarFuncionarioComSucesso() {
        UsuarioEntity usuario = new UsuarioEntity();

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        assertDoesNotThrow(() -> funcionarioService.deletarFuncionario(1));
        verify(usuarioRepository).delete(usuario);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar funcionário inexistente")
    void deveLancarExcecaoAoDeletarFuncionarioInexistente() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> funcionarioService.deletarFuncionario(1));
    }
}
