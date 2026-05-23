package com.faculty_appraisal.backend.model.dto.appraisal;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Map;

public record SubmitAppraisalRequest(
        @NotBlank String academicYear,
        Map<String, Object> form,
        Map<String, Object> payload,   // fallback if form is nested inside payload
        Map<String, Object> totals,
        Map<String, List<Map<String, Object>>> docs,
        String status
) {}