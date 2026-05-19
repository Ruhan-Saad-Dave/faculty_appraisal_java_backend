package com.faculty_appraisal.backend.model.entity.part_a;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "student_feedback")
@Data
public class StudentFeedback extends BasePartAModel {

    @Column(name = "row_no")
    private Integer rowNo;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "feedback_1", nullable = false)
    private BigDecimal feedback1 = BigDecimal.ZERO;

    @Column(name = "feedback_2", nullable = false)
    private BigDecimal feedback2 = BigDecimal.ZERO;
}