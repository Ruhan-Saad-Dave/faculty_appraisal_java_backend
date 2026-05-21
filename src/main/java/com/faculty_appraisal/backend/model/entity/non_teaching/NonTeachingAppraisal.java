package com.faculty_appraisal.backend.model.entity.non_teaching;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "non_teaching_appraisals")
@Data
public class NonTeachingAppraisal {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "staff_email", nullable = false)
    private String staffEmail;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String payload;

    @Column(nullable = false)
    private String status = "Draft";

    @Column(name = "self_total", nullable = false)
    private BigDecimal selfTotal = BigDecimal.ZERO;

    @Column(name = "ro_total", nullable = false)
    private BigDecimal roTotal = BigDecimal.ZERO;

    @Column(name = "registrar_total", nullable = false)
    private BigDecimal registrarTotal = BigDecimal.ZERO;

    @Column(name = "vc_total", nullable = false)
    private BigDecimal vcTotal = BigDecimal.ZERO;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "ro_reviewed_at")
    private LocalDateTime roReviewedAt;

    @Column(name = "registrar_reviewed_at")
    private LocalDateTime registrarReviewedAt;

    @Column(name = "vc_reviewed_at")
    private LocalDateTime vcReviewedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}