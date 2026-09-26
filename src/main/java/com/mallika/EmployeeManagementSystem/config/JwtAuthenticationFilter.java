package com.mallika.EmployeeManagementSystem.config;

import com.mallika.EmployeeManagementSystem.service.CustomUserDetailsService;
import com.mallika.EmployeeManagementSystem.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            CustomUserDetailsService userDetailsService) {

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println(
                ">>> REQUEST: " + request.getRequestURI()
        );

        String token = null;

        // ==========================================
        // 1. CHECK AUTHORIZATION HEADER
        // ==========================================

        String authHeader =
                request.getHeader("Authorization");

        if (authHeader != null &&
                authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);

            System.out.println(
                    ">>> JWT FOUND IN AUTHORIZATION HEADER"
            );
        }

        // ==========================================
        // 2. CHECK JWT COOKIE
        // ==========================================

        if (token == null &&
                request.getCookies() != null) {

            for (Cookie cookie : request.getCookies()) {

                if ("jwt".equals(cookie.getName())) {

                    token = cookie.getValue();

                    System.out.println(
                            ">>> JWT FOUND IN COOKIE"
                    );

                    break;
                }
            }
        }

        // ==========================================
        // 3. NO TOKEN
        // ==========================================

        if (token == null) {

            System.out.println(
                    ">>> NO JWT TOKEN FOUND"
            );

            filterChain.doFilter(request, response);
            return;
        }

        // ==========================================
        // 4. VALIDATE JWT
        // ==========================================

        try {

            String username =
                    jwtService.extractUsername(token);

            System.out.println(
                    ">>> JWT USERNAME: " + username
            );

            if (username != null &&
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication() == null) {

                UserDetails userDetails =
                        userDetailsService
                                .loadUserByUsername(username);

                System.out.println(
                        ">>> USER AUTHORITIES: "
                                + userDetails.getAuthorities()
                );

                if (jwtService.isTokenValid(token)) {

                    System.out.println(
                            ">>> JWT IS VALID"
                    );

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authentication.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authentication);

                    System.out.println(
                            ">>> USER AUTHENTICATED: "
                                    + username
                    );

                } else {

                    System.out.println(
                            ">>> JWT IS INVALID"
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    ">>> JWT ERROR: " + e.getMessage()
            );
        }

        filterChain.doFilter(request, response);
    }
}