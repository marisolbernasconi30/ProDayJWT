package com.app.proDay.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; //aca estan todos los filtros de seguridad, como el de autenticacion, autorizacion, etc
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration //le indica a spring que esta es una clase de configuración
@EnableWebSecurity //le indica a spring que esta clase va a configurar la seguridad de la aplicación, Activa Spring Security manualmente 
public class SecurityConfig {


    @Bean // Este método crea un objeto que Spring va a usar automáticamente
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // ❗ Desactiva CSRF (necesario en APIs REST sin formularios)git 
            .csrf(csrf -> csrf.disable())

            // 🔐 Configuración de autorización (quién puede acceder a qué)
            .authorizeHttpRequests(auth -> auth

                // ✅ Estas rutas NO necesitan autenticación
                .requestMatchers("/public/**").permitAll()

                // 🔒 Cualquier otra request necesita estar autenticado
                .anyRequest().authenticated()
            )

            // 🔑 Define el tipo de autenticación
            // En este caso: Basic Auth (usuario + contraseña en cada request)
            .httpBasic(withDefaults());

        // Construye y devuelve la configuración de seguridad
        return http.build();
    }


    // 👇 Define un usuario en memoria (para pruebas)
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User
            .withUsername("user") // usuario
            .password(passwordEncoder().encode("1234")) // contraseña encriptada
            .roles("USER") // rol
            .build();

        // Guarda el usuario en memoria
        return new InMemoryUserDetailsManager(user);
    }

    // 🔐 Bean para encriptar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


