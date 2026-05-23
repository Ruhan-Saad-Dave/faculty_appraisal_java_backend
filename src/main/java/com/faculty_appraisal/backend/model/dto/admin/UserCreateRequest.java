package com.faculty_appraisal.backend.model.dto.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateRequest {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String fullName;
    private String appraisalRole = "faculty";
    private String school;
    private String department;
    private String designation;
    private String employeeId;
    private String phone;
    private String qualification;
    private String teachingExperience;
    private boolean isVerified = true;
    private boolean isActive = true;
    private boolean reportsToRegistrar = false;
    private String reportingOfficerEmail;
    private String registrarEmail;
}
