package com.hospital.hospitalweb.repository;

import com.hospital.hospitalweb.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository
        extends JpaRepository<Patient, Long> {
}