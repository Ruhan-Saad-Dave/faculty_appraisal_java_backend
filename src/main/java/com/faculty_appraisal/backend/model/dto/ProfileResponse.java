package com.faculty_appraisal.backend.model.dto;

public record ProfileResponse(
        String email,
        String fullName,
        String appraisalRole,
        String department,
        String school,
        String employeeId,
        String designation,
        String qualification,
        String teachingExperience,
        String phone,
        String avatar
) {}
