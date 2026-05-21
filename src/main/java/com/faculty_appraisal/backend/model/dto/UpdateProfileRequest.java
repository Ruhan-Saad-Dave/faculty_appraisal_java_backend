package com.faculty_appraisal.backend.model.dto;

public record UpdateProfileRequest(
        String fullName,
        String employeeId,
        String qualification,
        String teachingExperience,
        String department,
        String school,
        String designation,
        String phone,
        String avatar
) {}
