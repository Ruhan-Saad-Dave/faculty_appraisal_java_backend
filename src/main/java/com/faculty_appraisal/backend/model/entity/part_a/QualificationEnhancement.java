package com.faculty_appraisal.backend.model.entity.part_a;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "qualification_enhancement")
@Data
public class QualificationEnhancement extends BasePartAModel {

    private String label;
}