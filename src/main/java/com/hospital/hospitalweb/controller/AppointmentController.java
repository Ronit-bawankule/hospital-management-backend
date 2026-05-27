package com.hospital.hospitalweb.controller;

import com.hospital.hospitalweb.model.Appointment;
import com.hospital.hospitalweb.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;

    public AppointmentController(
            AppointmentRepository appointmentRepository) {

        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @PostMapping
    public Appointment createAppointment(
            @RequestBody Appointment appointment) {

        return appointmentRepository.save(appointment);
    }
}