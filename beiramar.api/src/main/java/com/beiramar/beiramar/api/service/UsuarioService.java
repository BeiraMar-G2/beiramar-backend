package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.config.GerenciadorTokenJwt;
import com.beiramar.beiramar.api.dto.autenticacaoDtos.UsuarioLoginDto;
import com.beiramar.beiramar.api.dto.autenticacaoDtos.UsuarioTokenDto;
import com.beiramar.beiramar.api.dto.clienteDtos.ClienteCadastroDto;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.cargopersistence.CargoJpaRepository;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioEntity;
import com.beiramar.beiramar.api.infrastructure.persistence.usuariopersistence.UsuarioJpaRepository;
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

    public UsuarioTokenDto autenticar(UsuarioLoginDto dto) {

        String email = dto.getEmail();

        // Verifica bloqueio antes de tentar autenticar
        if (tentativasLoginService.estaBloqueado(email)) {
            long minutos = tentativasLoginService.minutosRestantes(email);
            throw new RuntimeException("Conta bloqueada. Tente novamente em " + minutos + " minutos.");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, dto.getSenha())
            );

            UsuarioEntity usuario = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            // Login bem-sucedido: limpa tentativas
            tentativasLoginService.limparTentativas(email);

            String token = gerenciadorTokenJwt.gerarToken(usuario);
            return new UsuarioTokenDto(
                    usuario.getIdUsuario(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    token,
                    usuario.getCargo().getNome(),
                    usuario.getTelefone()
            );

        } catch (Exception e) {
            tentativasLoginService.registrarFalha(email);
            throw new RuntimeException("Credenciais inválidas. Verifique seu e-mail e senha.");
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
