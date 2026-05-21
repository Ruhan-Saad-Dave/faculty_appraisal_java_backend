package com.faculty_appraisal.backend.model.entity.core;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appraisal_reviews")
@Data
public class AppraisalReview {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "faculty_email", nullable = false)
    private String facultyEmail;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(name = "reviewer_email")
    private String reviewerEmail;

    @Column(name = "reviewer_role", nullable = false)
    private String reviewerRole;

    @Column(name = "part_a_score", nullable = false)
    private BigDecimal partAScore = BigDecimal.ZERO;

    @Column(name = "part_b_score", nullable = false)
    private BigDecimal partBScore = BigDecimal.ZERO;

    @Column(name = "total_score", nullable = false)
    private BigDecimal totalScore = BigDecimal.ZERO;

    private String remarks;

    @Column(name = "section_scores", columnDefinition = "TEXT", nullable = false)
    private String  sectionScores;

    @Column(nullable = false)
    private String status;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt = LocalDateTime.now();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
