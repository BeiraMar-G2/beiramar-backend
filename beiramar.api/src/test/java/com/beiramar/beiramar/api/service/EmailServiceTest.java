package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.infrastructure.features.entity.LogSenhaEntity;
import com.beiramar.beiramar.api.infrastructure.features.entity.StatusLogSenha;
import com.beiramar.beiramar.api.infrastructure.features.repository.LogSenhaRepository;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do EmailService")
class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private LogSenhaRepository logSenhaRepository;

    @Mock
    private SendGrid sendGrid;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Deve enviar código de recuperação com sucesso")
    void enviarCodigoComSucesso() throws IOException {
        String email = "teste@example.com";
        String codigo = "123456";
        LogSenhaEntity log = new LogSenhaEntity();

        Request requestMock = mock(Request.class);
        when(sendGrid.api(any(Request.class))).thenReturn(null); // Simula envio sem erro

        emailService.enviarCodigo(email, codigo, log);

        assertEquals(StatusLogSenha.COD_ENVIADO, log.getStatus());
        verify(logSenhaRepository).save(log);
        verify(sendGrid).api(any(Request.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao enviar código quando API falha")
    void enviarCodigoComErroAoEnviarEmail() throws IOException {
        String email = "teste@example.com";
        String codigo = "123456";
        LogSenhaEntity log = new LogSenhaEntity();

        when(sendGrid.api(any(Request.class))).thenThrow(new IOException("Falha no envio"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> emailService.enviarCodigo(email, codigo, log));

        assertEquals("Erro ao enviar e-mail", exception.getMessage());
        assertEquals(StatusLogSenha.COD_ERRO, log.getStatus());
        verify(logSenhaRepository).save(log);
    }

    @Test
    @DisplayName("Deve enviar e-mail de autenticação com sucesso")
    void enviarAutenticacaoComSucesso() throws IOException {
        String email = "teste@example.com";

        Request requestMock = mock(Request.class);
        when(sendGrid.api(any(Request.class))).thenReturn(null); // Simula envio sem erro

        assertDoesNotThrow(() -> emailService.enviarAutenticacao(email));

        verify(sendGrid).api(any(Request.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao enviar e-mail de autenticação quando API falha")
    void enviarAutenticacaoComErroAoEnviarEmail() throws IOException {
        String email = "teste@example.com";

        when(sendGrid.api(any(Request.class))).thenThrow(new IOException("Falha no envio"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> emailService.enviarAutenticacao(email));

        assertEquals("Erro ao enviar e-mail", exception.getMessage());
    }
}
