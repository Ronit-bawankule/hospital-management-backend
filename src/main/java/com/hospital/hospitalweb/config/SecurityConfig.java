package com.hospital.hospitalweb.config;

import com.hospital.hospitalweb.security.JwtAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowedMethods(List.of("*"));

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .cors(cors -> {})

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // PUBLIC
                        .requestMatchers(
                                "/api/auth/login",
                                "/api/auth/forgot-password",
                                "/api/auth/reset-password"
                        ).permitAll()

                        // EXPORT
                        .requestMatchers("/api/export/**")
                        .hasRole("ADMIN")

                        // DASHBOARD
                        .requestMatchers("/api/dashboard/**")
                        .hasRole("ADMIN")

                        // INVENTORY
                        .requestMatchers("/api/inventory/**")
                        .hasRole("ADMIN")

                        // DOCTORS
                        .requestMatchers("/api/doctors/**")
                        .hasAnyRole("ADMIN", "DOCTOR")

                        // BILLING
                        .requestMatchers("/api/billing/**")
                        .hasAnyRole("ADMIN", "RECEPTIONIST")

                        // ROOMS
                        .requestMatchers("/api/rooms/**")
                        .hasAnyRole("ADMIN", "RECEPTIONIST")

                        // PATIENTS
                        .requestMatchers("/api/patients/**")
                        .hasAnyRole(
                                "ADMIN",
                                "DOCTOR",
                                "RECEPTIONIST"
                        )

                        // APPOINTMENTS
                        .requestMatchers("/api/appointments/**")
                        .hasAnyRole(
                                "ADMIN",
                                "DOCTOR",
                                "RECEPTIONIST"
                        )

                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}