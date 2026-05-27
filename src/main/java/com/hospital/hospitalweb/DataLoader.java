package com.hospital.hospitalweb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository userRepository) {

        return args -> {

            userRepository.deleteAll();

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setRole("ADMIN");
            userRepository.save(admin);

            User doctor = new User();
            doctor.setUsername("doctor");
            doctor.setPassword("doctor123");
            doctor.setRole("DOCTOR");
            userRepository.save(doctor);

            User reception = new User();
            reception.setUsername("reception");
            reception.setPassword("reception123");
            reception.setRole("RECEPTIONIST");
            userRepository.save(reception);
        };
    }
}