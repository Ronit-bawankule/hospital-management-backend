package com.hospital.hospitalweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    public void sendResetPasswordMail(String toEmail, String token) {

        if (mailSender == null) {
            System.out.println("MailSender not configured. Reset token: " + token);
            return;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Hospital HMS - Reset Password");
            message.setText(
                    "Use this token to reset your password:\n\n" +
                            token + "\n\n" +
                            "If you did not request this, ignore it."
            );

            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
            System.out.println("Reset token: " + token);
        }
    }
}