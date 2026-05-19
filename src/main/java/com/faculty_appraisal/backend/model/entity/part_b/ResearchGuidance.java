package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "research_guidance")
@Data
public class ResearchGuidance extends BasePartBModel {

    private String degree;

    @Column(name = "student_name")
    private String studentName;

    private String thesis;
}