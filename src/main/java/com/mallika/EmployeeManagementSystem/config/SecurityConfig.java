package com.mallika.EmployeeManagementSystem.config;

import com.mallika.EmployeeManagementSystem.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CustomUserDetailsService userDetailsService) throws Exception {

        http
                // Disable CSRF for REST API/Postman testing
                .csrf(csrf -> csrf.disable())

                // JWT is stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))

                // Authorization rules
                .authorizeHttpRequests(auth -> auth

                        // First user creation
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()

                        // ADMIN
                        .requestMatchers("/users/**")
                        .hasRole("ADMIN")

                        // EMPLOYEE → own data
                        .requestMatchers("/employees/me")
                        .hasRole("EMPLOYEE")

                        .requestMatchers("/attendance/me")
                        .hasRole("EMPLOYEE")

                        .requestMatchers("/leaves/me")
                        .hasRole("EMPLOYEE")

                        .requestMatchers("/payrolls/me")
                        .hasRole("EMPLOYEE")

                        .requestMatchers("/projects/me")
                        .hasRole("EMPLOYEE")

                        .requestMatchers("/tasks/me")
                        .hasRole("EMPLOYEE")

                        .requestMatchers("/teams/me/members")
                        .hasRole("EMPLOYEE")

                        .requestMatchers("/departments/me")
                        .hasRole("EMPLOYEE")

                        // ADMIN + HR
                        .requestMatchers("/employees/**")
                        .hasAnyRole("ADMIN", "HR")

                        .requestMatchers("/departments/**")
                        .hasAnyRole("ADMIN", "HR")

                        .requestMatchers("/designations/**")
                        .hasAnyRole("ADMIN", "HR")

                        .requestMatchers("/attendance/**")
                        .hasAnyRole("ADMIN", "HR")

                        .requestMatchers("/payrolls/**")
                        .hasAnyRole("ADMIN", "HR")

                        // ADMIN + HR + MANAGER
                        .requestMatchers("/teams/**")
                        .hasAnyRole("ADMIN", "HR", "MANAGER")

                        .requestMatchers("/projects/**")
                        .hasAnyRole("ADMIN", "HR", "MANAGER")

                        .requestMatchers("/tasks/**")
                        .hasAnyRole("ADMIN", "HR", "MANAGER")

                        .requestMatchers("/leaves/**")
                        .hasAnyRole("ADMIN", "HR","MANAGER")


                        // Everything else requires authentication
                        .anyRequest().authenticated()
                )

                // Run JWT filter before Spring's username/password filter
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }
}