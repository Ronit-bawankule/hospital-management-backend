package com.hospital.hospitalweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "http://localhost:3000")

public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public List<Patient> getPatients() {

        return patientRepository.findAll();
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {

        return patientRepository.save(patient);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable Long id,
            @RequestBody Patient updatedPatient
    ) {

        Patient patient = patientRepository.findById(id).orElse(null);

        if(patient != null) {

            patient.setName(updatedPatient.getName());

            patient.setAge(updatedPatient.getAge());

            patient.setDisease(updatedPatient.getDisease());

            patient.setDoctor(updatedPatient.getDoctor());

            return patientRepository.save(patient);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {

        patientRepository.deleteById(id);
    }
}