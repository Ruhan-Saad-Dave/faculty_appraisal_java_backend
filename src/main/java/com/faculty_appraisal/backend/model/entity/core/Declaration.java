package com.faculty_appraisal.backend.model.entity.core;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "declarations")
@Data
public class Declaration {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "faculty_email", nullable = false)
    private String facultyEmail;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(name = "part_a_total", nullable = false)
    private BigDecimal partATotal = BigDecimal.ZERO;

    @Column(name = "part_b_total", nullable = false)
    private BigDecimal partBTotal = BigDecimal.ZERO;

    @Column(name = "grand_total", nullable = false)
    private BigDecimal grandTotal = BigDecimal.ZERO;

    @Column(nullable = false)
    private String status = "Pending Review";

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt = LocalDateTime.now();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
