package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.config.GerenciadorTokenJwt;
import com.beiramar.beiramar.api.dto.autenticacaoDtos.UsuarioLoginDto;
import com.beiramar.beiramar.api.dto.autenticacaoDtos.UsuarioTokenDto;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;

    public UsuarioService(
            UsuarioJpaRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            GerenciadorTokenJwt gerenciadorTokenJwt) {
        this.usuarioRepository = usuarioRepository;
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
                usuario.getNome(),
                usuario.getEmail(),
                token,
                usuario.getCargo().getNome()
        );
    }
}
