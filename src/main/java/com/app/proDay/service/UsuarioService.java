package com.app.proDay.service;
/*
crea al usuario
*/
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.proDay.entity.Usuario;
import com.app.proDay.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario crearUsuario(String username, String password) {

        
        String passwordEncriptada = passwordEncoder.encode(password); //ASI SE ENCRIPTA LA CONTRASEÑA. nunca se tiene que desencriptar la contraseña

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncriptada);
        usuario.setRole("USER"); // rol por defecto

        return usuarioRepository.save(usuario);
    }
}

