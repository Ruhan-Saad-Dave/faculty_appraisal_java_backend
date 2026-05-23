package com.faculty_appraisal.backend.model.entity.part_a;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "teaching_process")
@Data
public class TeachingProcess extends BasePartAModel {

    private String semester;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "planned_classes", nullable = false)
    private BigDecimal plannedClasses = BigDecimal.ZERO;

    @Column(name = "conducted_classes", nullable = false)
    private BigDecimal conductedClasses = BigDecimal.ZERO;
}
