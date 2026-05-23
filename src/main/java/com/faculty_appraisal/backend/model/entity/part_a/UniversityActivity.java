package com.faculty_appraisal.backend.model.entity.part_a;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "university_activities")
@Data
public class UniversityActivity extends BasePartAModel {

    private String activity;

    private String nature;
}