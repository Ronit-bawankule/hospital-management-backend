package com.hospital.hospitalweb.service;

import com.hospital.hospitalweb.model.Doctor;
import com.hospital.hospitalweb.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(
            DoctorRepository doctorRepository
    ) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor addDoctor(
            Doctor doctor
    ) {

        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {

        return doctorRepository.findAll();
    }

    public Doctor updateDoctor(
            Long id,
            Doctor updatedDoctor
    ) {

        Doctor doctor =
                doctorRepository.findById(id)
                        .orElseThrow();

        doctor.setFirstName(
                updatedDoctor.getFirstName()
        );

        doctor.setLastName(
                updatedDoctor.getLastName()
        );

        doctor.setSpecialization(
                updatedDoctor.getSpecialization()
        );

        doctor.setExperience(
                updatedDoctor.getExperience()
        );

        doctor.setPhone(
                updatedDoctor.getPhone()
        );

        doctor.setEmail(
                updatedDoctor.getEmail()
        );

        doctor.setAvailability(
                updatedDoctor.getAvailability()
        );

        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(
            Long id
    ) {

        doctorRepository.deleteById(id);
    }
}