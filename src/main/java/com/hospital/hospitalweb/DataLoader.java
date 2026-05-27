package com.hospital.hospitalweb;

import com.hospital.hospitalweb.model.Role;
import com.hospital.hospitalweb.model.User;
import com.hospital.hospitalweb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedUser("Admin User",        "admin@hospital.com",     "admin123",     Role.ADMIN);
        seedUser("Dr. John Smith",    "doctor@hospital.com",    "doctor123",    Role.DOCTOR);
        seedUser("Reception Staff",   "reception@hospital.com", "reception123", Role.RECEPTIONIST);
    }

    private void seedUser(String name, String email, String password, Role role) {
        if (!userRepository.existsByEmail(email)) {
            User user = User.builder()
                    .name(name)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role(role)
                    .enabled(true)
                    .build();
            userRepository.save(user);
            logger.info("Seeded user: {} [{}]", email, role);
        } else {
            logger.info("User already exists: {}", email);
        }
    }
}