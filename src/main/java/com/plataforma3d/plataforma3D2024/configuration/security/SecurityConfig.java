package com.plataforma3d.plataforma3D2024.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    private final UserDetailServiceImpl userDetailsService;

    public SecurityConfig(UserDetailServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(htpp -> {
                    htpp.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll(); // Añade esta línea
                    //luego se configura los endpoint privados
                    htpp.requestMatchers(HttpMethod.POST,"/estudiante/new").hasRole("ADMIN");
                    htpp.requestMatchers(HttpMethod.PUT,"/estudiante/{id}").hasRole("ADMIN");
                    htpp.requestMatchers(HttpMethod.DELETE,"/estudiante/{id}").hasRole("ADMIN");
                    htpp.requestMatchers(HttpMethod.GET,"/estudiante/list").hasRole("ADMIN");
                    htpp.requestMatchers(HttpMethod.GET,"/estudiante/lista").hasRole("ADMIN");
                    htpp.requestMatchers(HttpMethod.GET, "/prueba/list").permitAll();
                    htpp.requestMatchers(HttpMethod.POST, "/api/media/upload").hasRole("ADMIN");
                    htpp.anyRequest().authenticated();
                })
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
