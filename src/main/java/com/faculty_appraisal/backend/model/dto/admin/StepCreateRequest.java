package com.faculty_appraisal.backend.model.dto.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StepCreateRequest {
    @NotBlank
    private String designationId;
    private Integer stepNo;
    private boolean isRequired = true;
}
