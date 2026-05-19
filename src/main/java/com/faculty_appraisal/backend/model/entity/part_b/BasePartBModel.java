package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BasePartBModel {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "faculty_email", nullable = false)
    private String facultyEmail;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(name = "form_family")
    private String formFamily;

    @Column(name = "section_title")
    private String sectionTitle;

    @Column(name = "max_marks")
    private BigDecimal maxMarks;

    @Column(name = "row_no")
    private Integer rowNo;

    @Column(nullable = false)
    private BigDecimal score = BigDecimal.ZERO;

    @Column(name = "hod_score")
    private BigDecimal hodScore;

    @Column(name = "director_score")
    private BigDecimal directorScore;

    @Column(name = "dean_score")
    private BigDecimal deanScore;

    @Column(name = "vc_score")
    private BigDecimal vcScore;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}