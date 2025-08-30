package com.beiramar.beiramar.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.beiramar.beiramar.api.dto.autenticacaoDtos.UsuarioDetalhesDto;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AutenticacaoServiceTest {

    @Mock
    private UsuarioJpaRepository usuarioRepository;

    @InjectMocks
    private AutenticacaoService autenticacaoService;

    @Test
    void deveCarregarUsuarioPorEmailComSucesso() {
        // Arrange
        String email = "usuario@teste.com";

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setIdUsuario(1);
        usuario.setEmail(email);
        usuario.setNome("João Silva");
        usuario.setSenha("senha123");

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        // Act
        UserDetails resultado = autenticacaoService.loadUserByUsername(email);

        // Assert
        assertNotNull(resultado);
        assertInstanceOf(UsuarioDetalhesDto.class, resultado);
        assertEquals(email, resultado.getUsername());

        // Verify
        verify(usuarioRepository).findByEmail(email);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoForEncontrado() {
        // Arrange
        String email = "usuario@inexistente.com";

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> autenticacaoService.loadUserByUsername(email)
        );

        assertEquals("Usuário não encontrado", exception.getMessage());

        // Verify
        verify(usuarioRepository).findByEmail(email);
    }

    @Test
    void deveCarregarUsuarioComEmailVazio() {
        // Arrange
        String email = "";

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> autenticacaoService.loadUserByUsername(email)
        );

        assertEquals("Usuário não encontrado", exception.getMessage());

        // Verify
        verify(usuarioRepository).findByEmail(email);
    }

    @Test
    void deveCarregarUsuarioComEmailNull() {
        // Arrange
        String email = null;

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> autenticacaoService.loadUserByUsername(email)
        );

        assertEquals("Usuário não encontrado", exception.getMessage());

        // Verify
        verify(usuarioRepository).findByEmail(email);
    }
}