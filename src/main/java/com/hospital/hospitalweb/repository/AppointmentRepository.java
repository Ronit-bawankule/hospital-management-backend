package com.hospital.hospitalweb.repository;

import com.hospital.hospitalweb.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {
}