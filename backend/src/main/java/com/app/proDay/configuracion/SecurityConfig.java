package com.app.proDay.configuracion;
/*
ACA VA LA CONFIGURACION DE SPRING, DEFINO LAS RUTAS PROTEGIDAS, STATELESS, FILTROS, ETC
*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; //aca estan todos los filtros de seguridad, como el de autenticacion, autorizacion, etc
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
 
 
@Configuration //le indica a spring que esta es una clase de configuración
@EnableWebSecurity //le indica a spring que esta clase va a configurar la seguridad de la aplicación, Activa Spring Security manualmente
public class SecurityConfig {
 
 
    @Bean // Este método crea un objeto que Spring va a usar automáticamente, lo inyecta donde hace falta
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 
        http
            // Desactiva CSRF (API REST)
            .csrf(csrf -> csrf.disable())
 
            // Configuración de rutas. Spring aplica la PRIMERA regla que coincide,
            // por eso van de la más específica a la más general.
            .authorizeHttpRequests(auth -> auth
 
                // 🔓 Pública: solo el registro (antes era todo /usuarios/**)
                .requestMatchers(HttpMethod.POST, "/usuarios/register").permitAll()
 
                // 👑 Solo ADMIN. hasRole("ADMIN") busca la authority "ROLE_ADMIN",
                // que es la que arma CustomUserDetailsService
                .requestMatchers("/admin/**").hasRole("ADMIN")
 
                // 🔒 Todo lo demás requiere estar autenticado (cualquier rol)
                .anyRequest().authenticated()
            )
 
            // 🔑 Tipo de autenticación (por ahora Basic Auth)
            .httpBasic(Customizer.withDefaults());
 
        return http.build();
    }
 
}