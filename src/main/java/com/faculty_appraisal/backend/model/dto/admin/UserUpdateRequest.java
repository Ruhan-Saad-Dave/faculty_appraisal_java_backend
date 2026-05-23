package com.faculty_appraisal.backend.model.dto.admin;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String fullName;
    private String appraisalRole;
    private String school;
    private String department;
    private String designation;
    private String employeeId;
    private String phone;
    private String qualification;
    private String teachingExperience;
    private Boolean isVerified;
    private Boolean isActive;
    private Boolean reportsToRegistrar;
    private String reportingOfficerEmail;
    private String registrarEmail;
    private String password;
}
