package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.dto.clienteDtos.ClienteAtualizacaoDto;
import com.beiramar.beiramar.api.dto.clienteDtos.ClienteCadastroDto;
import com.beiramar.beiramar.api.dto.clienteDtos.ClienteListagemDto;
import com.beiramar.beiramar.api.dto.mapper.ClienteMapper;
import com.beiramar.beiramar.api.entity.Cargo;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.exception.EntidadeNaoEncontradaException;
import com.beiramar.beiramar.api.repository.CargoRepository;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
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
@DisplayName("Testes do ClienteService")
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CargoRepository cargoRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Deve cadastrar cliente com sucesso")
    void deveCadastrarClienteComSucesso() {
        ClienteCadastroDto dto = new ClienteCadastroDto();
        dto.setSenha("senha123");

        Cargo cargoCliente = new Cargo();
        Usuario clienteEntity = new Usuario();
        Usuario usuarioSalvo = new Usuario();
        ClienteListagemDto clienteDto = new ClienteListagemDto();

        when(cargoRepository.findByNomeIgnoreCase("CLIENTE")).thenReturn(Optional.of(cargoCliente));
        when(passwordEncoder.encode(dto.getSenha())).thenReturn("senhaCriptografada");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        try (MockedStatic<ClienteMapper> mocked = mockStatic(ClienteMapper.class)) {
            mocked.when(() -> ClienteMapper.toEntity(dto, cargoCliente)).thenReturn(clienteEntity);
            mocked.when(() -> ClienteMapper.toDto(usuarioSalvo)).thenReturn(clienteDto);

            ClienteListagemDto resultado = clienteService.cadastrarCliente(dto);

            assertNotNull(resultado);
            verify(usuarioRepository).save(clienteEntity);
            mocked.verify(() -> ClienteMapper.toEntity(dto, cargoCliente));
            mocked.verify(() -> ClienteMapper.toDto(usuarioSalvo));
        }
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar cliente sem cargo CLIENTE")
    void deveLancarExcecaoAoCadastrarClienteSemCargoCliente() {
        ClienteCadastroDto dto = new ClienteCadastroDto();
        when(cargoRepository.findByNomeIgnoreCase("CLIENTE")).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.cadastrarCliente(dto));
    }

    @Test
    @DisplayName("Deve listar todos os clientes com sucesso")
    void deveListarTodosClientesComSucesso() {
        Cargo cargoCliente = new Cargo();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        ClienteListagemDto dto1 = new ClienteListagemDto();
        ClienteListagemDto dto2 = new ClienteListagemDto();

        when(cargoRepository.findByNomeIgnoreCase("CLIENTE")).thenReturn(Optional.of(cargoCliente));
        when(usuarioRepository.findByCargo(cargoCliente)).thenReturn(List.of(usuario1, usuario2));

        try (MockedStatic<ClienteMapper> mocked = mockStatic(ClienteMapper.class)) {
            mocked.when(() -> ClienteMapper.toDto(usuario1)).thenReturn(dto1);
            mocked.when(() -> ClienteMapper.toDto(usuario2)).thenReturn(dto2);

            List<ClienteListagemDto> resultado = clienteService.listarTodosClientes();

            assertEquals(2, resultado.size());
            verify(usuarioRepository).findByCargo(cargoCliente);
        }
    }

    @Test
    @DisplayName("Deve lançar exceção ao listar clientes sem cargo CLIENTE")
    void deveLancarExcecaoAoListarClientesSemCargoCliente() {
        when(cargoRepository.findByNomeIgnoreCase("CLIENTE")).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.listarTodosClientes());
    }

    @Test
    @DisplayName("Deve buscar cliente por ID com sucesso")
    void deveBuscarClientePorIdComSucesso() {
        Usuario usuario = new Usuario();
        ClienteListagemDto clienteDto = new ClienteListagemDto();

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        try (MockedStatic<ClienteMapper> mocked = mockStatic(ClienteMapper.class)) {
            mocked.when(() -> ClienteMapper.toDto(usuario)).thenReturn(clienteDto);

            ClienteListagemDto resultado = clienteService.buscarPorId(1);

            assertNotNull(resultado);
            verify(usuarioRepository).findById(1);
        }
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar cliente por ID inexistente")
    void deveLancarExcecaoAoBuscarClientePorIdInexistente() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.buscarPorId(1));
    }

    @Test
    @DisplayName("Deve atualizar cliente com sucesso")
    void deveAtualizarClienteComSucesso() {
        Usuario usuario = new Usuario();
        ClienteAtualizacaoDto dto = new ClienteAtualizacaoDto();
        ClienteListagemDto dtoAtualizado = new ClienteListagemDto();

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        try (MockedStatic<ClienteMapper> mocked = mockStatic(ClienteMapper.class)) {
            mocked.when(() -> ClienteMapper.atualizarEntity(usuario, dto)).thenAnswer(inv -> {
                usuario.setNome(dto.getNome());
                usuario.setEmail(dto.getEmail());
                return null;
            });

            mocked.when(() -> ClienteMapper.toDto(usuario)).thenReturn(dtoAtualizado);

            ClienteListagemDto resultado = clienteService.atualizarCliente(1, dto);

            assertNotNull(resultado);
            verify(usuarioRepository).save(usuario);
        }
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar cliente inexistente")
    void deveLancarExcecaoAoAtualizarClienteInexistente() {
        ClienteAtualizacaoDto dto = new ClienteAtualizacaoDto();

        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.atualizarCliente(1, dto));
    }

    @Test
    @DisplayName("Deve deletar cliente com sucesso")
    void deveDeletarClienteComSucesso() {
        Usuario usuario = new Usuario();

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        assertDoesNotThrow(() -> clienteService.deletarCliente(1));
        verify(usuarioRepository).delete(usuario);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar cliente inexistente")
    void deveLancarExcecaoAoDeletarClienteInexistente() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.deletarCliente(1));
    }
}
