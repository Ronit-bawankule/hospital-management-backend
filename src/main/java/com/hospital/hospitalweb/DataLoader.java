package com.hospital.hospitalweb;

import com.hospital.hospitalweb.model.Role;
import com.hospital.hospitalweb.model.User;
import com.hospital.hospitalweb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByEmail("admin@hospital.com").isEmpty()) {

            User admin = new User(
                    "Admin",
                    "admin@hospital.com",
                    passwordEncoder.encode("admin123"),
                    Role.ADMIN
            );

            User doctor = new User(
                    "Doctor",
                    "doctor@hospital.com",
                    passwordEncoder.encode("doctor123"),
                    Role.DOCTOR
            );

            User reception = new User(
                    "Reception",
                    "reception@hospital.com",
                    passwordEncoder.encode("reception123"),
                    Role.RECEPTIONIST
            );

            userRepository.save(admin);
            userRepository.save(doctor);
            userRepository.save(reception);

            System.out.println("Default users created.");
        }
    }
}