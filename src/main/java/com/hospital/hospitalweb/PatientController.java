package com.hospital.hospitalweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")

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

            patient.setNurseName(updatedPatient.getNurseName());

            patient.setNurseId(updatedPatient.getNurseId());

            patient.setXray(updatedPatient.isXray());

            patient.setMri(updatedPatient.isMri());

            patient.setOperation(updatedPatient.isOperation());

            patient.setIcu(updatedPatient.isIcu());

            patient.setIcuDays(updatedPatient.getIcuDays());

            return patientRepository.save(patient);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {

        patientRepository.deleteById(id);
    }
}