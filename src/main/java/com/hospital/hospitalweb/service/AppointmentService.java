package com.hospital.hospitalweb.service;

import com.hospital.hospitalweb.model.Appointment;
import com.hospital.hospitalweb.model.Doctor;
import com.hospital.hospitalweb.model.Patient;

import com.hospital.hospitalweb.repository.AppointmentRepository;
import com.hospital.hospitalweb.repository.DoctorRepository;
import com.hospital.hospitalweb.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;

    private final MailService mailService;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            MailService mailService
    ) {

        this.appointmentRepository =
                appointmentRepository;

        this.patientRepository =
                patientRepository;

        this.doctorRepository =
                doctorRepository;

        this.mailService =
                mailService;
    }

    public Appointment addAppointment(
            Appointment appointment
    ) {

        Long patientId =
                appointment.getPatient().getId();

        Long doctorId =
                appointment.getDoctor().getId();

        Patient patient =
                patientRepository
                        .findById(patientId)
                        .orElseThrow();

        Doctor doctor =
                doctorRepository
                        .findById(doctorId)
                        .orElseThrow();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment savedAppointment =
                appointmentRepository.save(
                        appointment
                );

        mailService.sendAppointmentConfirmationEmail(
                savedAppointment
        );

        return savedAppointment;
    }

    public List<Appointment> getAllAppointments() {

        return appointmentRepository.findAll();
    }

    public void deleteAppointment(
            Long id
    ) {

        appointmentRepository.deleteById(id);
    }
}