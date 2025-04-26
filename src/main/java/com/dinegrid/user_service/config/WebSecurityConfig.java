package com.dinegrid.user_service.config;

import com.dinegrid.user_service.security.JwtAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Modern way to disable CSRF
//                .authorizeHttpRequests((requests) -> requests
//                        .anyRequest().permitAll()
//                );
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        "/api/v1/auth/**",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**"
                                ).permitAll()
                       .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
