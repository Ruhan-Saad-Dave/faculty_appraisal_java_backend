package com.faculty_appraisal.backend.model.entity.core;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "appraisal_config")
@Data
public class AppraisalConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "academic_year", nullable = false, unique = true)
    private String academicYear;

    @Column(name = "is_open", nullable = false)
    private Boolean isOpen = false;

    @Column(name = "submission_start")
    private LocalDateTime submissionStart;

    @Column(name = "submission_end")
    private LocalDateTime submissionEnd;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
