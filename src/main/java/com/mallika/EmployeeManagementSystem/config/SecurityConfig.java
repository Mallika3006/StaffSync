package com.mallika.EmployeeManagementSystem.config;

import com.mallika.EmployeeManagementSystem.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

                // CSRF disabled for now
                .csrf(csrf -> csrf.disable())

                // Authorization rules
                .authorizeHttpRequests(auth -> auth

                        // LOGIN PAGES
                        .requestMatchers(
                                "/admin-login",
                                "/employee-login",
                                "/perform-login",
                                "/login"
                        ).permitAll()

                        // First user creation
                        .requestMatchers(HttpMethod.POST, "/users")
                        .permitAll()

                        // DASHBOARDS
                        // Only ADMIN
                        .requestMatchers("/admin-dashboard")
                        .hasRole("ADMIN")

                        // Only HR
                        .requestMatchers("/hr-dashboard")
                        .hasRole("HR")

                        // Only MANAGER
                        .requestMatchers("/manager-dashboard")
                        .hasRole("MANAGER")

                        // EMPLOYEE + HR + MANAGER
                        .requestMatchers("/employee-dashboard")
                        .hasAnyRole("EMPLOYEE", "HR", "MANAGER")

                        // ADMIN
                        .requestMatchers("/users/**")
                        .hasRole("ADMIN")

                        // EMPLOYEE DASHBOARD
                        // Own data
                        // EMPLOYEE + HR + MANAGER
                        .requestMatchers("/employees/me")
                        .hasAnyRole("EMPLOYEE", "HR", "MANAGER")

                        .requestMatchers("/attendance/me")
                        .hasAnyRole("EMPLOYEE", "HR", "MANAGER")

                        .requestMatchers("/leaves/me")
                        .hasAnyRole("EMPLOYEE", "HR", "MANAGER")

                        .requestMatchers("/payrolls/me")
                        .hasAnyRole("EMPLOYEE", "HR", "MANAGER")

                        .requestMatchers("/projects/me")
                        .hasAnyRole("EMPLOYEE", "HR", "MANAGER")

                        .requestMatchers("/tasks/me")
                        .hasAnyRole("EMPLOYEE", "HR", "MANAGER")

                        .requestMatchers("/teams/me/members")
                        .hasAnyRole("EMPLOYEE", "HR", "MANAGER")

                        .requestMatchers("/departments/me")
                        .hasAnyRole("EMPLOYEE", "HR", "MANAGER")

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
                        .hasAnyRole("ADMIN", "HR", "MANAGER")

                        // EVERYTHING ELSE
                        .anyRequest().authenticated()
                )

                // FORM LOGIN
                .formLogin(form -> form

                        .loginPage("/admin-login")

                        .loginProcessingUrl("/perform-login")

                        .successHandler((request, response, authentication) -> {

                            String role = authentication.getAuthorities()
                                    .iterator()
                                    .next()
                                    .getAuthority();

                            if (role.equals("ROLE_ADMIN")) {

                                response.sendRedirect("/admin-dashboard");

                            } else if (role.equals("ROLE_HR")) {

                                response.sendRedirect("/hr-dashboard");

                            } else if (role.equals("ROLE_MANAGER")) {

                                response.sendRedirect("/manager-dashboard");

                            } else {

                                response.sendRedirect("/employee-dashboard");
                            }
                        })

                        .failureUrl("/admin-login?error=true")

                        .permitAll()
                )

                // JWT FILTER
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    // PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // AUTHENTICATION MANAGER
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }
}