package com.hospital.hospitalweb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository userRepository) {

        return args -> {

            if(userRepository.findByUsername("admin") == null) {

                User admin = new User();

                admin.setUsername("admin");

                admin.setPassword("admin123");

                admin.setRole("ADMIN");

                userRepository.save(admin);
            }

            if(userRepository.findByUsername("doctor") == null) {

                User doctor = new User();

                doctor.setUsername("doctor");

                doctor.setPassword("doctor123");

                doctor.setRole("DOCTOR");

                userRepository.save(doctor);
            }

            if(userRepository.findByUsername("reception") == null) {

                User reception = new User();

                reception.setUsername("reception");

                reception.setPassword("reception123");

                reception.setRole("RECEPTIONIST");

                userRepository.save(reception);
            }
        };
    }
}