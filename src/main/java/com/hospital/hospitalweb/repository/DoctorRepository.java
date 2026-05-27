package com.hospital.hospitalweb.repository;

import com.hospital.hospitalweb.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}