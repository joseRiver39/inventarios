

package com.inventarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors()  // habilita CORS con la configuración que tengas
            .and()
            .csrf().disable()  // deshabilita CSRF (útil para APIs REST)
            .authorizeHttpRequests()
                .anyRequest().permitAll()  // permite todas las peticiones sin autenticación
            .and()
            .httpBasic().disable()  // deshabilita autenticación básica
            .formLogin().disable(); // deshabilita formulario de login

        return http.build();
    }
}