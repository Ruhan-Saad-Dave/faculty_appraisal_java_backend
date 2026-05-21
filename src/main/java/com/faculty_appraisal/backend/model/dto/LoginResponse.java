package com.faculty_appraisal.backend.model.dto;

public record LoginResponse(
        String token,
        ProfileResponse profile
) {}
