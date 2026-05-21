package com.faculty_appraisal.backend.model.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(
        @NotBlank String token,
        @NotBlank String newPassword
) {}
