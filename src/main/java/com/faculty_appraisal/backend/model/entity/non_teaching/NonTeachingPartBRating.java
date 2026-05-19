package com.faculty_appraisal.backend.model.entity.non_teaching;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "non_teaching_part_b_ratings")
@Data
public class NonTeachingPartBRating {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "staff_email", nullable = false)
    private String staffEmail;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(name = "section_key", nullable = false)
    private String sectionKey;

    @Column(name = "section_title", nullable = false)
    private String sectionTitle;

    @Column(name = "max_marks", nullable = false)
    private BigDecimal maxMarks;

    @Column(name = "parameter_no", nullable = false)
    private Integer parameterNo;

    @Column(name = "parameter_label", nullable = false)
    private String parameterLabel;

    @Column(name = "ro_rating")
    private BigDecimal roRating;

    @Column(name = "registrar_rating")
    private BigDecimal registrarRating;

    @Column(name = "vc_rating")
    private BigDecimal vcRating;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}