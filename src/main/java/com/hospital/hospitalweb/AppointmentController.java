package com.hospital.hospitalweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:3000")

public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping
    public List<Appointment> getAppointments() {

        return appointmentRepository.findAll();
    }

    @PostMapping
    public Appointment createAppointment(
            @RequestBody Appointment appointment
    ) {

        return appointmentRepository.save(appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(
            @PathVariable Long id
    ) {

        appointmentRepository.deleteById(id);
    }
}