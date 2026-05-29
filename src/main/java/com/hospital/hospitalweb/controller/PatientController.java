package com.hospital.hospitalweb.controller;

import com.hospital.hospitalweb.model.Patient;
import com.hospital.hospitalweb.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin("*")
public class PatientController {

    private final PatientService patientService;

    public PatientController(
            PatientService patientService
    ) {
        this.patientService = patientService;
    }

    @PostMapping
    public Patient addPatient(
            @RequestBody Patient patient
    ) {

        return patientService.addPatient(patient);
    }

    @GetMapping
    public List<Patient> getAllPatients() {

        return patientService.getAllPatients();
    }

    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient
    ) {

        return patientService.updatePatient(
                id,
                patient
        );
    }

    @DeleteMapping("/{id}")
    public void deletePatient(
            @PathVariable Long id
    ) {

        patientService.deletePatient(id);
    }
}