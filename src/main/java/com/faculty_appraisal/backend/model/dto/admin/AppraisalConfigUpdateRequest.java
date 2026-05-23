package com.faculty_appraisal.backend.model.dto.admin;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppraisalConfigUpdateRequest {
    private Boolean isOpen;
    private LocalDateTime submissionStart;
    private LocalDateTime submissionEnd;
}
