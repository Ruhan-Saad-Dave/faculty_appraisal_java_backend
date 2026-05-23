package com.faculty_appraisal.backend.model.dto.appraisal;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Map;

public record SnapshotRequest(
        @NotBlank String academicYear,
        Map<String, Object> payload,
        Map<String, List<Map<String, Object>>> docs
) {}

