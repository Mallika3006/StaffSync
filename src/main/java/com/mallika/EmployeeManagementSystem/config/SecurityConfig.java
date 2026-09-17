package com.mallika.EmployeeManagementSystem.config;

import com.mallika.EmployeeManagementSystem.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CustomUserDetailsService userDetailsService) throws Exception {

        http
                // Disable CSRF for API/Postman testing
                .csrf(csrf -> csrf.disable())

                // Use HTTP session when required
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.IF_REQUIRED))

                // All endpoints require login
                .authorizeHttpRequests(auth -> auth
                        // First user creation
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()

                        // ADMIN
                        .requestMatchers("/users/**").hasRole("ADMIN")

                        // EMPLOYEE → only their own profile
                        .requestMatchers("/employees/me").hasRole("EMPLOYEE")
                        .requestMatchers("/attendance/me").hasRole("EMPLOYEE")
                        .requestMatchers("/leaves/me").hasRole("EMPLOYEE")
                        .requestMatchers("/payrolls/me").hasRole("EMPLOYEE")
                        .requestMatchers("/projects/me").hasRole("EMPLOYEE")
                        .requestMatchers("/tasks/me").hasRole("EMPLOYEE")
                        .requestMatchers("/teams/me/members").hasRole("EMPLOYEE")
                        .requestMatchers("/departments/me").hasRole("EMPLOYEE")

                        // ADMIN + HR
                        .requestMatchers("/employees/**").hasAnyRole("ADMIN", "HR")
                        .requestMatchers("/departments/**").hasAnyRole("ADMIN", "HR")
                        .requestMatchers("/designations/**").hasAnyRole("ADMIN", "HR")
                        .requestMatchers("/attendance/**").hasAnyRole("ADMIN", "HR")
                        .requestMatchers("/leaves/**").hasAnyRole("ADMIN", "HR")
                        .requestMatchers("/payrolls/**").hasAnyRole("ADMIN", "HR")

                        // ADMIN + HR + MANAGER
                        .requestMatchers("/teams/**").hasAnyRole("ADMIN", "HR", "MANAGER")
                        .requestMatchers("/projects/**").hasAnyRole("ADMIN", "HR", "MANAGER")
                        .requestMatchers("/tasks/**").hasAnyRole("ADMIN", "HR", "MANAGER")


                        // Any logged-in user
                        .anyRequest().authenticated()
                )

                // Login page for browser
                .formLogin(Customizer.withDefaults())

                // Basic authentication for Postman
                .httpBasic(Customizer.withDefaults())

                // Logout
                .logout(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}