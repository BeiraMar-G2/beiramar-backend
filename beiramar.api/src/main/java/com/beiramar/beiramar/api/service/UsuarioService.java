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

    public UsuarioService(
            UsuarioJpaRepository usuarioRepository,
            CargoJpaRepository cargoRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            GerenciadorTokenJwt gerenciadorTokenJwt) {
        this.usuarioRepository = usuarioRepository;
        this.cargoRepository = cargoRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
        );

        UsuarioEntity usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String token = gerenciadorTokenJwt.gerarToken(usuario);
        return new UsuarioTokenDto(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                token,
                usuario.getCargo().getNome()
        );
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
                usuario.getCargo().getNome()
        );
    }
}
