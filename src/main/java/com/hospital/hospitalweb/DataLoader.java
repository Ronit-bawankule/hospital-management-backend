package com.hospital.hospitalweb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            createIfMissing(userRepository, passwordEncoder, "Admin User", "admin@hospital.com", "admin123", "ADMIN");
            createIfMissing(userRepository, passwordEncoder, "Doctor User", "doctor@hospital.com", "doctor123", "DOCTOR");
            createIfMissing(userRepository, passwordEncoder, "Reception User", "reception@hospital.com", "reception123", "RECEPTIONIST");
        };
    }

    private void createIfMissing(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            String fullName,
            String email,
            String password,
            String role
    ) {
        if (userRepository.findByEmail(email).isEmpty()) {
            User user = new User();
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            userRepository.save(user);
        }
    }
}