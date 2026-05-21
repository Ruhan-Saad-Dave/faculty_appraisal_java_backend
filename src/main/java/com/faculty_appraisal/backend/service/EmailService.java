package com.faculty_appraisal.backend.service;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.url}")
    private String appUrl;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    @Value("${spring.mail.username}")
    private String mailFrom;

    public void sendVerificationEmail(String toEmail, String token) {
        String verifyUrl = appUrl + "/api/v1/auth/verify-email?token=" + token;
        String html = """
            <h3>Welcome to the Faculty Appraisal System</h3>
            <p>Please verify your email address by clicking the link below:</p>
            <a href="%s">Verify Email Address</a>
            <br><br>
            <p>If you did not create an account, please ignore this email.</p>
            """.formatted(verifyUrl);
        sendHtmlEmail(toEmail, "Email Verification - Faculty Appraisal System", html);
    }

    public void sendResetEmail(String toEmail, String resetUrl) {
        String html = """
            <h3>Faculty Appraisal System — Password Reset</h3>
            <p>You requested a password reset. Click the link below to set a new password:</p>
            <a href="%s">Reset Password</a>
            <br><br>
            <p>This link expires in 1 hour. If you did not request a reset, ignore this email.</p>
            """.formatted(resetUrl);
        sendHtmlEmail(toEmail, "Password Reset — Faculty Appraisal System", html);
    }

    private void sendHtmlEmail(String to, String subject, String html) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailFrom, "Faculty Appraisal System");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);
            mailSender.send(message);
        } catch (Exception e) {
            // log but don't crash — same behaviour as Python backend
            log.error("Failed to send email to {}: {}", to, e.getMessage());
        }
    }
}

