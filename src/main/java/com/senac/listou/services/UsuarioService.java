package com.senac.listou.services;

import com.senac.listou.entidades.Usuario;
import com.senac.listou.repositorios.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepositorio usuarioRepositorio;

    public Optional<Usuario> findByUsernameOptional(String login) {
        return usuarioRepositorio.findByUsername(login);
    }
}
