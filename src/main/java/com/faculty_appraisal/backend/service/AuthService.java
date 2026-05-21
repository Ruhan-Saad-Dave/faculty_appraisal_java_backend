package com.faculty_appraisal.backend.service;

import com.faculty_appraisal.backend.exception.AppException;
import com.faculty_appraisal.backend.model.dto.*;
import com.faculty_appraisal.backend.model.entity.core.FacultyProfile;
import com.faculty_appraisal.backend.model.entity.core.PasswordResetToken;
import com.faculty_appraisal.backend.repository.core.FacultyProfileRepository;
import com.faculty_appraisal.backend.repository.core.PasswordResetTokenRepository;
import com.faculty_appraisal.backend.security.JwtUtils;
import com.faculty_appraisal.backend.security.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final FacultyProfileRepository facultyRepo;
    private final PasswordResetTokenRepository resetTokenRepo;
    private final JwtUtils jwtUtils;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final LoginAttemptService loginAttemptService;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    // ── Login ──────────────────────────────────────────────────────────────
    public LoginResponse login(LoginRequest request) {
        if (loginAttemptService.isBlocked(request.email()))
            throw new AppException(429, "Too many login attempts. Please try again in a minute.");

        FacultyProfile user = facultyRepo.findByEmail(request.email())
                .orElse(null);

        if (user == null || !passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            loginAttemptService.loginFailed(request.email());
            throw new AppException(401, "Invalid email or password");
        }

        if (!user.isVerified())
            throw new AppException(403, "Email not verified. Please check your inbox.");

        loginAttemptService.loginSucceeded(request.email());
        String token = jwtUtils.generateToken(user.getEmail(), user.getAppraisalRole());
        return new LoginResponse(token, toProfileResponse(user));
    }

    // ── Register ───────────────────────────────────────────────────────────
    public Map<String, String> register(RegisterRequest request) {
        if (facultyRepo.findByEmail(request.email()).isPresent())
            throw new AppException(400, "Email already registered");

        FacultyProfile user = new FacultyProfile();
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setFullName(request.fullName());
        user.setAppraisalRole(request.appraisalRole());
        user.setSchool(request.school());
        user.setDepartment(request.department());
        user.setDesignation(request.designation());
        user.setEmployeeId(request.employeeId());
        user.setPhone(request.phone());
        user.setQualification(request.qualification());
        user.setTeachingExperience(request.teachingExperience());
        user.setVerified(false);
        facultyRepo.save(user);

        // Generate a 24-hour verification token with purpose claim
        String verifyToken = jwtUtils.generateVerificationToken(user.getEmail());
        try {
            emailService.sendVerificationEmail(user.getEmail(), verifyToken);
        } catch (Exception e) {
            log.error("Failed to send verification email to {}: {}", user.getEmail(), e.getMessage());
        }

        return Map.of(
                "message", "Registration successful. Please check your email to verify your account.",
                "email", user.getEmail()
        );
    }

    // ── Verify Email ───────────────────────────────────────────────────────
    public String verifyEmail(String token) {
        String loginUrl = frontendUrl + "/login";
        try {
            if (!jwtUtils.isTokenValid(token))
                return loginUrl + "?error=invalid_token";

            // Block API tokens from being used for verification
            if (!jwtUtils.isVerificationToken(token))
                return loginUrl + "?error=invalid_token";

            String email = jwtUtils.extractEmail(token);
            FacultyProfile user = facultyRepo.findByEmail(email).orElse(null);
            if (user == null)
                return loginUrl + "?error=user_not_found";

            if (!user.isVerified()) {
                user.setVerified(true);
                facultyRepo.save(user);
            }
            return loginUrl + "?verified=true";
        } catch (Exception e) {
            log.error("Email verification failed: {}", e.getMessage());
            return loginUrl + "?error=verification_failed";
        }
    }

    // ── Get Profile ────────────────────────────────────────────────────────
    public ProfileResponse getProfile(String email) {
        FacultyProfile user = facultyRepo.findByEmail(email)
                .orElseThrow(() -> new AppException(404, "User not found"));
        return toProfileResponse(user);
    }

    // ── Update Profile ─────────────────────────────────────────────────────
    public ProfileResponse updateProfile(String email, UpdateProfileRequest request) {
        FacultyProfile user = facultyRepo.findByEmail(email)
                .orElseThrow(() -> new AppException(404, "User not found"));

        if (request.fullName()           != null) user.setFullName(request.fullName());
        if (request.employeeId()         != null) user.setEmployeeId(request.employeeId());
        if (request.qualification()      != null) user.setQualification(request.qualification());
        if (request.teachingExperience() != null) user.setTeachingExperience(request.teachingExperience());
        if (request.department()         != null) user.setDepartment(request.department());
        if (request.school()             != null) user.setSchool(request.school());
        if (request.designation()        != null) user.setDesignation(request.designation());
        if (request.phone()              != null) user.setPhone(request.phone());
        if (request.avatar()             != null) user.setAvatar(request.avatar());

        facultyRepo.save(user);
        return toProfileResponse(user);
    }

    // ── Change Password ────────────────────────────────────────────────────
    public Map<String, String> changePassword(String email, ChangePasswordRequest request) {
        FacultyProfile user = facultyRepo.findByEmail(email)
                .orElseThrow(() -> new AppException(404, "User not found"));

        if (!passwordEncoder.matches(request.currentPassword(), user.getPasswordHash()))
            throw new AppException(400, "Incorrect current password");

        user.setPasswordHash(passwordEncoder.encode(request.newPassword()));
        facultyRepo.save(user);
        return Map.of("message", "Password changed successfully");
    }

    // ── Forgot Password ────────────────────────────────────────────────────
    public Map<String, String> forgotPassword(ForgotPasswordRequest request) {
        // Always return 200 — prevents email enumeration
        String safeMessage = "If that email is registered, a reset link has been sent.";
        String email = request.email().trim().toLowerCase();

        facultyRepo.findByEmail(email).ifPresent(user -> {
            String rawToken = generateSecureToken();
            String tokenHash = sha256(rawToken);
            LocalDateTime expiresAt = LocalDateTime.now().plusHours(1);

            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setEmail(email);
            resetToken.setTokenHash(tokenHash);
            resetToken.setExpiresAt(expiresAt);
            resetToken.setUsed(false);
            resetTokenRepo.save(resetToken);

            String resetUrl = resolveResetUrl(request.redirectUrl()) + "?token=" + rawToken;
            try {
                emailService.sendResetEmail(email, resetUrl);
            } catch (Exception e) {
                log.error("Failed to send reset email to {}: {}", email, e.getMessage());
            }
        });

        return Map.of("message", safeMessage);
    }

    // ── Reset Password ─────────────────────────────────────────────────────
    public Map<String, String> resetPassword(ResetPasswordRequest request) {
        String tokenHash = sha256(request.token());

        PasswordResetToken resetToken = resetTokenRepo.findByTokenHash(tokenHash)
                .orElseThrow(() -> new AppException(400, "Invalid, expired, or already used reset token"));

        if (resetToken.isUsed() || resetToken.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new AppException(400, "Invalid, expired, or already used reset token");

        FacultyProfile user = facultyRepo.findByEmail(resetToken.getEmail())
                .orElseThrow(() -> new AppException(400, "User not found"));

        user.setPasswordHash(passwordEncoder.encode(request.newPassword()));
        resetToken.setUsed(true);
        facultyRepo.save(user);
        resetTokenRepo.save(resetToken);

        return Map.of("message", "Password reset successfully.");
    }

    // ── Helpers ────────────────────────────────────────────────────────────
    private ProfileResponse toProfileResponse(FacultyProfile u) {
        return new ProfileResponse(
                u.getEmail(), u.getFullName(), u.getAppraisalRole(),
                u.getDepartment(), u.getSchool(), u.getEmployeeId(),
                u.getDesignation(), u.getQualification(), u.getTeachingExperience(),
                u.getPhone(), u.getAvatar()
        );
    }

    private String generateSecureToken() {
        return java.util.UUID.randomUUID().toString().replace("-", "") +
                java.util.UUID.randomUUID().toString().replace("-", "");
    }

    private String sha256(String input) {
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException("SHA-256 error", e);
        }
    }

    private String resolveResetUrl(String redirectUrl) {
        String defaultUrl = frontendUrl + "/reset-password";
        if (redirectUrl == null || redirectUrl.isBlank()) return defaultUrl;
        try {
            String allowedHost = new java.net.URI(frontendUrl).getHost();
            String requestedHost = new java.net.URI(redirectUrl).getHost();
            return allowedHost.equals(requestedHost) ? redirectUrl.stripTrailing() : defaultUrl;
        } catch (Exception e) {
            return defaultUrl;
        }
    }
}

