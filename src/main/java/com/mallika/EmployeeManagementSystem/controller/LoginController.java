package com.mallika.EmployeeManagementSystem.controller;

import com.mallika.EmployeeManagementSystem.dto.LoginRequest;
import com.mallika.EmployeeManagementSystem.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginController(
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // ==========================================
    // ADMIN LOGIN PAGE
    // ==========================================

    @GetMapping("/admin-login")
    public String adminLoginPage() {
        System.out.println(">>> ADMIN LOGIN PAGE REACHED");

        return "login/admin-login";
    }


    // ==========================================
    // USER LOGIN PAGE
    // ==========================================

    @GetMapping("/user-login")
    public String userLoginPage() {
        System.out.println(">>> USER LOGIN PAGE REACHED");
        return "login/user-login";
    }


    // ==========================================
    // AUTHENTICATE USER
    // ==========================================

    @PostMapping("/login")
    @ResponseBody
    public Map<String, String> login(
            @RequestBody LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token =
                jwtService.generateToken(
                        userDetails.getUsername()
                );

        String role =
                userDetails.getAuthorities()
                        .iterator()
                        .next()
                        .getAuthority();

        return Map.of(
                "token", token,
                "role", role
        );
    }
}