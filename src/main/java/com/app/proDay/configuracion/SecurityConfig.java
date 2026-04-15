package com.app.proDay.configuracion;
/*
ACA VA LA CONFIGURACION DE SPRING, DEFINO LAS RUTAS PROTEGIDAS, STATELESS, FILTROS, ETC
*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; //aca estan todos los filtros de seguridad, como el de autenticacion, autorizacion, etc
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;


@Configuration //le indica a spring que esta es una clase de configuración
@EnableWebSecurity //le indica a spring que esta clase va a configurar la seguridad de la aplicación, Activa Spring Security manualmente 
public class SecurityConfig {


    @Bean // Este método crea un objeto que Spring va a usar automáticamente, lo inyecta donde hace falta
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

         http
            //  Desactiva CSRF (API REST)
            .csrf(csrf -> csrf.disable())

            //  Configuración de rutas
            .authorizeHttpRequests(auth -> auth

                // 🔓 Rutas públicas (si tenés alguna)
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/usuarios/**").permitAll()

                // 🔒 Todo lo demás requiere autenticación
                .anyRequest().authenticated()
            )

            // 🔑 Tipo de autenticación (por ahora Basic Auth)
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    // 🔐 Bean para encriptar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


