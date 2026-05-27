package com.hospital.hospitalweb.controller;

import com.hospital.hospitalweb.dto.LoginRequest;
import com.hospital.hospitalweb.dto.LoginResponse;
import com.hospital.hospitalweb.model.User;
import com.hospital.hospitalweb.repository.UserRepository;
import com.hospital.hospitalweb.security.JwtUtils;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public AuthController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            JwtUtils jwtUtils) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow();

        String token =
                jwtUtils.generateToken(user.getEmail());

        return ResponseEntity.ok(
                new LoginResponse(
                        token,
                        user.getId(),
                        user.getRole().name(),
                        user.getName(),
                        user.getEmail()
                )
        );
    }
}