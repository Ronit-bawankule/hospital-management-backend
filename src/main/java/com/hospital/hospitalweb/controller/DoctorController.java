package com.hospital.hospitalweb.controller;

import com.hospital.hospitalweb.model.Doctor;
import com.hospital.hospitalweb.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin("*")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(
            DoctorService doctorService
    ) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public Doctor addDoctor(
            @RequestBody Doctor doctor
    ) {

        return doctorService.addDoctor(doctor);
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {

        return doctorService.getAllDoctors();
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(
            @PathVariable Long id,
            @RequestBody Doctor doctor
    ) {

        return doctorService.updateDoctor(
                id,
                doctor
        );
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(
            @PathVariable Long id
    ) {

        doctorService.deleteDoctor(id);
    }
}