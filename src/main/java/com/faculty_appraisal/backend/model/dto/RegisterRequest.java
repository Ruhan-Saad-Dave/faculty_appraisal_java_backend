package com.faculty_appraisal.backend.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank String fullName,
        @NotBlank String appraisalRole,
        String school,
        String department,
        String designation,
        String employeeId,
        String phone,
        String qualification,
        String teachingExperience
) {}