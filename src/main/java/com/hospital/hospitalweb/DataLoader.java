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

    public DataLoader(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        userRepository.deleteAll();

        createUser(
                "Admin",
                "admin@hospital.com",
                "admin123",
                Role.ADMIN
        );

        createUser(
                "Doctor",
                "doctor@hospital.com",
                "doctor123",
                Role.DOCTOR
        );

        createUser(
                "Reception",
                "reception@hospital.com",
                "reception123",
                Role.RECEPTIONIST
        );

        System.out.println("Default users created successfully.");
    }

    private void createUser(
            String name,
            String email,
            String password,
            Role role
    ) {

        User user = new User();

        user.setName(name);
        user.setEmail(email);

        user.setPassword(
                passwordEncoder.encode(password)
        );

        user.setRole(role);

        userRepository.save(user);
    }
}