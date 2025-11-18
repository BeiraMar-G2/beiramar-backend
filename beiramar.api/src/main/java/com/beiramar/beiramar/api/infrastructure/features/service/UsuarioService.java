package com.beiramar.beiramar.api.infrastructure.features.service;

import com.beiramar.beiramar.api.config.GerenciadorTokenJwt;
import com.beiramar.beiramar.api.infrastructure.features.dto.autenticacaoDtos.UsuarioLoginDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.autenticacaoDtos.UsuarioTokenDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.clienteDtos.ClienteCadastroDto;
import com.beiramar.beiramar.api.infrastructure.features.dto.notificacaoDtos.ErroDto;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioJpaRepository usuarioRepository;
    private final CargoJpaRepository cargoRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final TentativasLoginService tentativasLoginService;

    public UsuarioService(UsuarioJpaRepository usuarioRepository,
                          CargoJpaRepository cargoRepository, PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager, GerenciadorTokenJwt gerenciadorTokenJwt,
                          TentativasLoginService tentativasLoginService) {
        this.usuarioRepository = usuarioRepository;
        this.cargoRepository = cargoRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.tentativasLoginService = tentativasLoginService;
    }

    public ResponseEntity<?> autenticar(UsuarioLoginDto dto) {
        String email = dto.getEmail();

        // 1. Verifica bloqueio
        if (tentativasLoginService.estaBloqueado(email)) {
            long minutos = tentativasLoginService.minutosRestantes(email);
            return ResponseEntity
                    .status(429)
                    .body(new ErroDto("Conta bloqueada. Tente novamente em " + minutos + " minutos."));
        }

        try {
            // 2. Tenta autenticar
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, dto.getSenha())
            );

            UsuarioEntity usuario = usuarioRepository.findByEmail(email)
                    .orElse(null);

            if (usuario == null) {
                return ResponseEntity
                        .status(401)
                        .body(new ErroDto("Credenciais inválidas."));
            }

            // 3. Limpa tentativas após sucesso
            tentativasLoginService.limparTentativas(email);

            String token = gerenciadorTokenJwt.gerarToken(usuario);

            UsuarioTokenDto tokenDto = new UsuarioTokenDto(
                    usuario.getIdUsuario(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    token,
                    usuario.getCargo().getNome(),
                    usuario.getTelefone()
            );

            return ResponseEntity.ok(tokenDto);

        } catch (Exception e) {
            // 4. Registra falha
            tentativasLoginService.registrarFalha(email);

            return ResponseEntity
                    .status(401)
                    .body(new ErroDto("Credenciais inválidas. Verifique seu e-mail e senha."));
        }
    }


    public UsuarioTokenDto autenticarOuCriarComGoogle(ClienteCadastroDto dto) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseGet(() -> {

                    UsuarioEntity novoUsuario = new UsuarioEntity();
                    novoUsuario.setNome(dto.getNome());
                    novoUsuario.setEmail(dto.getEmail());
                    novoUsuario.setSenha(passwordEncoder.encode("google-auth"));
                    novoUsuario.setTelefone(dto.getTelefone() != null ? dto.getTelefone() : "0000000000");
                    novoUsuario.setCargo(cargoRepository.findByIdCargo(dto.getFkCargo()));

                    return usuarioRepository.save(novoUsuario);
                });

        // Gera token JWT
        String token = gerenciadorTokenJwt.gerarToken(usuario);

        return new UsuarioTokenDto(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                token,
                usuario.getCargo().getNome(),
                usuario.getTelefone()
        );
    }
}
