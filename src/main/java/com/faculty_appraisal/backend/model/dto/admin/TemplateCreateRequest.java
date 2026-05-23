package com.faculty_appraisal.backend.model.dto.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TemplateCreateRequest {
    @NotBlank
    private String name;
    private String description;
}
