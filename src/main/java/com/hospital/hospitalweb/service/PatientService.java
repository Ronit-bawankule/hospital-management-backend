package com.hospital.hospitalweb.service;

import com.hospital.hospitalweb.model.Patient;
import com.hospital.hospitalweb.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(
            PatientRepository patientRepository
    ) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(
            Patient patient
    ) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {

        return patientRepository.findAll();
    }

    public Patient updatePatient(
            Long id,
            Patient updatedPatient
    ) {

        Patient patient =
                patientRepository.findById(id)
                        .orElseThrow();

        patient.setFirstName(
                updatedPatient.getFirstName()
        );

        patient.setLastName(
                updatedPatient.getLastName()
        );

        patient.setAge(
                updatedPatient.getAge()
        );

        patient.setGender(
                updatedPatient.getGender()
        );

        patient.setPhone(
                updatedPatient.getPhone()
        );

        patient.setEmail(
                updatedPatient.getEmail()
        );

        patient.setAddress(
                updatedPatient.getAddress()
        );

        patient.setBloodGroup(
                updatedPatient.getBloodGroup()
        );

        return patientRepository.save(patient);
    }

    public void deletePatient(
            Long id
    ) {

        patientRepository.deleteById(id);
    }
}