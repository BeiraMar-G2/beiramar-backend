package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.config.GerenciadorTokenJwt;
import com.beiramar.beiramar.api.dto.autenticacaoDtos.UsuarioLoginDto;
import com.beiramar.beiramar.api.dto.autenticacaoDtos.UsuarioTokenDto;
import com.beiramar.beiramar.api.entity.Cargo;
import com.beiramar.beiramar.api.entity.Usuario;
import com.beiramar.beiramar.api.repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do UsuarioService")
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Deve autenticar usuário com sucesso")
    void autenticar_ComSucesso() {
        UsuarioLoginDto loginDto = new UsuarioLoginDto("teste@example.com", "senha123");
        Usuario usuario = new Usuario();
        usuario.setNome("Teste");
        usuario.setEmail("teste@example.com");
        usuario.setCargo(new Cargo());
        usuario.getCargo().setNome("ADMIN");

        when(usuarioRepository.findByEmail(loginDto.getEmail())).thenReturn(Optional.of(usuario));
        when(gerenciadorTokenJwt.gerarToken(usuario)).thenReturn("token-jwt-gerado");

        usuarioService.autenticar(loginDto);

        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getSenha())
        );
        verify(usuarioRepository).findByEmail(loginDto.getEmail());
        verify(gerenciadorTokenJwt).gerarToken(usuario);
    }

    @Test
    @DisplayName("Deve lançar exceção ao autenticar usuário inexistente")
    void autenticar_UsuarioNaoEncontrado() {
        UsuarioLoginDto loginDto = new UsuarioLoginDto("inexistente@example.com", "senha123");

        when(usuarioRepository.findByEmail(loginDto.getEmail())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> usuarioService.autenticar(loginDto));

        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getSenha())
        );
        verify(usuarioRepository).findByEmail(loginDto.getEmail());
        verify(gerenciadorTokenJwt, never()).gerarToken(any(Usuario.class));
    }
}
