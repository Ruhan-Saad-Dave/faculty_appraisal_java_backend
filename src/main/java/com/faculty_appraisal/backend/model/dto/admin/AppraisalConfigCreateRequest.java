package com.faculty_appraisal.backend.model.dto.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppraisalConfigCreateRequest {
    @NotBlank
    private String academicYear;
    private boolean isOpen = false;
    private LocalDateTime submissionStart;
    private LocalDateTime submissionEnd;
}
