package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.entity.LogSenhaEntity;
import com.beiramar.beiramar.api.entity.StatusLogSenha;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.repository.LogSenhaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do RecuperacaoSenhaService")
class RecuperacaoSenhaServiceTest {

    @InjectMocks
    private RecuperacaoSenhaService recuperacaoSenhaService;

    @Mock
    private UsuarioJpaRepository usuarioRepository;

    @Mock
    private LogSenhaRepository logSenhaRepository;

    @Mock
    private EmailService emailService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Deve enviar código de recuperação com sucesso")
    void enviarCodigoComSucesso() {
        String email = "teste@example.com";
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail(email);

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        recuperacaoSenhaService.enviarCodigo(email);

        verify(logSenhaRepository).save(any(LogSenhaEntity.class));
        verify(emailService).enviarCodigo(eq(email), anyString(), any(LogSenhaEntity.class));
    }

    @Test
    @DisplayName("Não deve enviar código se usuário não existir")
    void enviarCodigoUsuarioNaoEncontrado() {
        String email = "teste@example.com";

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        recuperacaoSenhaService.enviarCodigo(email);

        verify(logSenhaRepository, never()).save(any(LogSenhaEntity.class));
        verify(emailService, never()).enviarCodigo(anyString(), anyString(), any(LogSenhaEntity.class));
    }

    @Test
    @DisplayName("Deve validar código com sucesso")
    void validarCodigoComSucesso() {
        String email = "teste@example.com";
        String codigo = "123456";
        LogSenhaEntity logSenha = new LogSenhaEntity();
        logSenha.setToken(codigo);
        logSenha.setUsuario(new UsuarioEntity());
        logSenha.setDataHoraLogSenha(LocalDateTime.now().minusMinutes(5)); // Dentro do tempo válido
        logSenha.setStatus(StatusLogSenha.COD_CRIADO);

        when(logSenhaRepository.findTopByTokenAndUsuarioEmailOrderByDataHoraLogSenhaDesc(codigo, email))
                .thenReturn(Optional.of(logSenha));

        boolean resultado = recuperacaoSenhaService.validarCodigo(email, codigo);

        assertTrue(resultado);
        assertEquals(StatusLogSenha.COD_ALTENTICADO, logSenha.getStatus());
        verify(logSenhaRepository).save(logSenha);
        verify(emailService).enviarAutenticacao(email);
    }

    @Test
    @DisplayName("Não deve validar código inexistente")
    void validarCodigoCodigoInexistente() {
        String email = "teste@example.com";
        String codigo = "654321";

        when(logSenhaRepository.findTopByTokenAndUsuarioEmailOrderByDataHoraLogSenhaDesc(codigo, email))
                .thenReturn(Optional.empty());

        boolean resultado = recuperacaoSenhaService.validarCodigo(email, codigo);

        assertFalse(resultado);
        verify(logSenhaRepository, never()).save(any(LogSenhaEntity.class));
        verify(emailService, never()).enviarAutenticacao(anyString());
    }

    @Test
    @DisplayName("Não deve validar código expirado")
    void validarCodigoCodigoExpirado() {
        String email = "teste@example.com";
        String codigo = "123456";
        LogSenhaEntity logSenha = new LogSenhaEntity();
        logSenha.setToken(codigo);
        logSenha.setUsuario(new UsuarioEntity());
        logSenha.setDataHoraLogSenha(LocalDateTime.now().minusMinutes(15)); // Código expirado
        logSenha.setStatus(StatusLogSenha.COD_CRIADO);

        when(logSenhaRepository.findTopByTokenAndUsuarioEmailOrderByDataHoraLogSenhaDesc(codigo, email))
                .thenReturn(Optional.of(logSenha));

        boolean resultado = recuperacaoSenhaService.validarCodigo(email, codigo);

        assertFalse(resultado);
        verify(logSenhaRepository, never()).save(any(LogSenhaEntity.class));
        verify(emailService, never()).enviarAutenticacao(anyString());
    }
}
