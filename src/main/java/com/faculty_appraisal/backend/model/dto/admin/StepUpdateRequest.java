package com.faculty_appraisal.backend.model.dto.admin;

import lombok.Data;

@Data
public class StepUpdateRequest {
    private String designationId;
    private Boolean isRequired;
}
