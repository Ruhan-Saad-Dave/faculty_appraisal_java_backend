package com.faculty_appraisal.backend.model.dto.admin;

import lombok.Data;

@Data
public class TemplateUpdateRequest {
    private String name;
    private String description;
    private Boolean isActive;
}
