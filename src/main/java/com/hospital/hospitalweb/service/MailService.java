package com.hospital.hospitalweb.service;

import com.hospital.hospitalweb.model.Appointment;
import com.hospital.hospitalweb.model.Patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Logger logger =
            LoggerFactory.getLogger(MailService.class);

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordResetEmail(
            String toEmail,
            String resetLink) {

        try {

            SimpleMailMessage message =
                    new SimpleMailMessage();

            message.setFrom(fromEmail);
            message.setTo(toEmail);

            message.setSubject(
                    "Hospital Management System - Password Reset Request"
            );

            message.setText(
                    "Hello,\n\n" +
                            "A password reset was requested for your account.\n\n" +
                            "Click the link below to reset your password:\n\n" +
                            resetLink + "\n\n" +
                            "This link expires in 1 hour.\n\n" +
                            "If you did not request this, please ignore this email.\n\n" +
                            "Regards,\nHospital Management System"
            );

            mailSender.send(message);

        } catch (Exception e) {

            logger.error(
                    "Failed to send password reset email: {}",
                    e.getMessage()
            );
        }
    }

    public void sendAppointmentConfirmationEmail(
            Appointment appointment) {

        try {

            Patient patient =
                    appointment.getPatient();

            if (patient == null ||
                    patient.getEmail() == null ||
                    patient.getEmail().isBlank()) {

                return;
            }

            SimpleMailMessage message =
                    new SimpleMailMessage();

            message.setFrom(fromEmail);

            message.setTo(
                    patient.getEmail()
            );

            message.setSubject(
                    "Appointment Confirmation"
            );

            message.setText(
                    "Dear "
                            + patient.getFirstName()
                            + " "
                            + patient.getLastName()
                            + ",\n\n"

                            + "Your appointment has been confirmed.\n\n"

                            + "Doctor: "
                            + appointment.getDoctor().getFirstName()
                            + " "
                            + appointment.getDoctor().getLastName()
                            + "\n"

                            + "Date: "
                            + appointment.getAppointmentDate()
                            + "\n"

                            + "Time: "
                            + appointment.getAppointmentTime()
                            + "\n"

                            + "Status: "
                            + appointment.getStatus()
                            + "\n\n"

                            + "Thank you.\n"
                            + "Hospital Management System"
            );

            mailSender.send(message);

        } catch (Exception e) {

            logger.error(
                    "Failed to send appointment email: {}",
                    e.getMessage()
            );
        }
    }

    public void sendLowInventoryAlert(
            String medicineName,
            Integer quantity) {

        try {

            SimpleMailMessage message =
                    new SimpleMailMessage();

            message.setFrom(fromEmail);

            message.setTo(fromEmail);

            message.setSubject(
                    "LOW INVENTORY ALERT"
            );

            message.setText(
                    "Medicine: "
                            + medicineName
                            + "\n\nRemaining Quantity: "
                            + quantity
                            + "\n\nPlease restock immediately."
            );

            mailSender.send(message);

            logger.info(
                    "Low inventory alert sent for {}",
                    medicineName
            );

        } catch (Exception e) {

            logger.error(
                    "Failed to send inventory alert: {}",
                    e.getMessage()
            );
        }
    }
}