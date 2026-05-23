package com.faculty_appraisal.backend.model.dto.admin;

import lombok.Data;

@Data
public class DesignationUpdateRequest {
    private String name;
    private String description;
    private Boolean isActive;
}
