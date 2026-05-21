package com.faculty_appraisal.backend.model.entity.core;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="faculty_profiles")
@Data
public class FacultyProfile {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    private String qualification;

    private String designation;

    private String department;

    private String school;

    @Column(name = "teaching_experience")
    private String teachingExperience;

    private String phone;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "appraisal_role", nullable = false)
    private String appraisalRole = "faculty";

    @Column(name = "is_verified")
    private boolean isVerified = false;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "reports_to_registrar", nullable = false)
    private boolean reportsToRegistrar = false;

    @Column(name = "reporting_officer_email")
    private String reportingOfficerEmail;

    @Column(name = "registrar_email")
    private String registrarEmail;

    private String avatar;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
