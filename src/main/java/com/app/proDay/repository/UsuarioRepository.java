package com.app.proDay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.proDay.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // 🔹 método clave para login
    Optional<Usuario> findByUsername(String username);
}