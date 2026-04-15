package com.app.proDay.controller;

import org.springframework.web.bind.annotation.*;

import com.app.proDay.dto.UsuarioRequest;
import com.app.proDay.entity.Usuario;
import com.app.proDay.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody UsuarioRequest request) {

        return usuarioService.crearUsuario(
                request.getUsername(),
                request.getPassword()
        );
    }
}
