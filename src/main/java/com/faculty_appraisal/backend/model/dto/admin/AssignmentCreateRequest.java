package com.faculty_appraisal.backend.model.dto.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AssignmentCreateRequest {
    @NotBlank
    private String templateId;
    private String staffEmail;
    private String appraisalRole;
    private String department;
}
