package com.app.proDay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.proDay.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //  método del login que busca por nombre en la base de datos
    Optional<Usuario> findByUsername(String username);
}