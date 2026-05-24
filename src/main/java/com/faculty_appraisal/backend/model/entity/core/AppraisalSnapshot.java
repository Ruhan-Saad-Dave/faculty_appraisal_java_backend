package com.faculty_appraisal.backend.model.entity.core;

import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appraisal_snapshots")
@Data
public class AppraisalSnapshot {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "faculty_email", nullable = false)
    private String facultyEmail;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @JsonRawValue
    @ColumnTransformer(write = "?::jsonb")
    @Column(columnDefinition = "jsonb", nullable = false)
    private String payload;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}