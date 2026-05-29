package com.hospital.hospitalweb.controller;

import com.hospital.hospitalweb.model.Appointment;
import com.hospital.hospitalweb.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin("*")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(
            AppointmentService appointmentService
    ) {
        this.appointmentService =
                appointmentService;
    }

    @PostMapping
    public Appointment addAppointment(
            @RequestBody Appointment appointment
    ) {

        return appointmentService.addAppointment(
                appointment
        );
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {

        return appointmentService
                .getAllAppointments();
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(
            @PathVariable Long id
    ) {

        appointmentService.deleteAppointment(id);
    }
}