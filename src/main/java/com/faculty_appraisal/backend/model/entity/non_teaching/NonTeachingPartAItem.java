package com.faculty_appraisal.backend.model.entity.non_teaching;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "non_teaching_part_a_items")
@Data
public class NonTeachingPartAItem {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "staff_email", nullable = false)
    private String staffEmail;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(name = "item_key", nullable = false)
    private String itemKey;

    @Column(nullable = false)
    private String title;

    @Column(name = "max_marks", nullable = false)
    private BigDecimal maxMarks;

    private String details;

    @Column(name = "self_marks")
    private BigDecimal selfMarks;

    @Column(name = "ro_marks")
    private BigDecimal roMarks;

    @Column(name = "registrar_marks")
    private BigDecimal registrarMarks;

    @Column(name = "vc_marks")
    private BigDecimal vcMarks;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}