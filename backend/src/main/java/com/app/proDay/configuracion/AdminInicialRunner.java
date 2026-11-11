package com.app.proDay.configuracion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
 
import com.app.proDay.entity.Usuario;
import com.app.proDay.entity.enums.Role;
import com.app.proDay.repository.UsuarioRepository;
 

@Component
public class AdminInicialRunner implements CommandLineRunner {
 
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
 
    public AdminInicialRunner(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
 
    @Override
    public void run(String... args) {
 
        // existsByRole pregunta directo a la base; no hace falta traer todos los usuarios
        if (!usuarioRepository.existsByRole(Role.ADMIN)) {
            Usuario admin = new Usuario("admin", passwordEncoder.encode("cambiar123"), Role.ADMIN);
            usuarioRepository.save(admin);
            System.out.println("Usuario ADMIN inicial creado: admin / cambiar123");
        }
    }
}