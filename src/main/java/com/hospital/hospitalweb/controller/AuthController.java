package com.hospital.hospitalweb.controller;

import com.hospital.hospitalweb.dto.*;
import com.hospital.hospitalweb.model.PasswordResetToken;
import com.hospital.hospitalweb.model.User;
import com.hospital.hospitalweb.repository.PasswordResetTokenRepository;
import com.hospital.hospitalweb.repository.UserRepository;
import com.hospital.hospitalweb.security.JwtUtils;
import com.hospital.hospitalweb.service.MailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MailService mailService;

    @Value("${app.frontendUrl}")
    private String frontendUrl;

    // ─── Login ───────────────────────────────────────────────────────────────
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow();

            return ResponseEntity.ok(new LoginResponse(
                    jwt,
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole().name()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(401)
                    .body(Map.of("message", "Invalid email or password"));
        }
    }

    // ─── Forgot Password ─────────────────────────────────────────────────────
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request) {

        // Always return same message to prevent email enumeration
        String safeMsg = "If that email is registered, a reset link has been sent.";

        return userRepository.findByEmail(request.getEmail())
                .map(user -> {
                    // Remove existing tokens for this user
                    tokenRepository.deleteByUser_Id(user.getId());

                    String token = UUID.randomUUID().toString();
                    PasswordResetToken resetToken = PasswordResetToken.builder()
                            .token(token)
                            .user(user)
                            .expiryDate(LocalDateTime.now().plusHours(1))
                            .build();
                    tokenRepository.save(resetToken);

                    String resetLink = frontendUrl + "/reset-password?token=" + token;

                    try {
                        mailService.sendPasswordResetEmail(user.getEmail(), resetLink);
                    } catch (Exception e) {
                        return ResponseEntity.status(500)
                                .body(Map.of("message",
                                        "Failed to send email. Please check mail config."));
                    }

                    return ResponseEntity.ok(Map.of("message", safeMsg));
                })
                .orElse(ResponseEntity.ok(Map.of("message", safeMsg)));
    }

    // ─── Reset Password ──────────────────────────────────────────────────────
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request) {

        return tokenRepository.findByToken(request.getToken())
                .map(resetToken -> {
                    if (resetToken.isExpired()) {
                        tokenRepository.delete(resetToken);
                        return ResponseEntity.badRequest()
                                .body(Map.of("message",
                                        "Reset link has expired. Please request a new one."));
                    }

                    User user = resetToken.getUser();
                    user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                    userRepository.save(user);
                    tokenRepository.delete(resetToken);

                    return ResponseEntity.ok(
                            Map.of("message", "Password reset successfully. You can now log in."));
                })
                .orElse(ResponseEntity.badRequest()
                        .body(Map.of("message", "Invalid or expired reset link.")));
    }

    // ─── Current User ─────────────────────────────────────────────────────────
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401)
                    .body(Map.of("message", "Not authenticated"));
        }
        return userRepository.findByEmail(userDetails.getUsername())
                .map(user -> ResponseEntity.ok(Map.of(
                        "id",    user.getId(),
                        "name",  user.getName(),
                        "email", user.getEmail(),
                        "role",  user.getRole().name()
                )))
                .orElse(ResponseEntity.notFound().build());
    }
}