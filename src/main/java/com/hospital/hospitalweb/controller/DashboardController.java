package com.hospital.hospitalweb.controller;

import com.hospital.hospitalweb.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;

    private final AppointmentRepository appointmentRepository;

    private final BillingRepository billingRepository;

    private final RoomRepository roomRepository;

    private final InventoryRepository inventoryRepository;

    public DashboardController(
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            AppointmentRepository appointmentRepository,
            BillingRepository billingRepository,
            RoomRepository roomRepository,
            InventoryRepository inventoryRepository
    ) {

        this.patientRepository =
                patientRepository;

        this.doctorRepository =
                doctorRepository;

        this.appointmentRepository =
                appointmentRepository;

        this.billingRepository =
                billingRepository;

        this.roomRepository =
                roomRepository;

        this.inventoryRepository =
                inventoryRepository;
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {

        Map<String, Object> stats =
                new HashMap<>();

        stats.put(
                "patients",
                patientRepository.count()
        );

        stats.put(
                "doctors",
                doctorRepository.count()
        );

        stats.put(
                "appointments",
                appointmentRepository.count()
        );

        stats.put(
                "rooms",
                roomRepository.count()
        );

        stats.put(
                "inventory",
                inventoryRepository.count()
        );

        double revenue =
                billingRepository.findAll()
                        .stream()
                        .mapToDouble(bill ->
                                bill.getAmount() != null
                                        ? bill.getAmount()
                                        : 0
                        )
                        .sum();

        stats.put("revenue", revenue);

        return stats;
    }
}