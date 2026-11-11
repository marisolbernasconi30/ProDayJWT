package com.app.proDay.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
@Configuration
public class ApplicationConfig {
 
    // Spring Security arma solo el proveedor de autenticación a partir de
    // CustomUserDetailsService (busca al usuario) + este PasswordEncoder (compara contraseñas).
    // Por eso acá no hace falta definir ni el UserDetailsService ni el AuthenticationProvider.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}