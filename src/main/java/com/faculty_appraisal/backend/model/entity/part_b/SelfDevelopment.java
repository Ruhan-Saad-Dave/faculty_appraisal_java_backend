package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "self_development")
@Data
public class SelfDevelopment extends BasePartBModel {

    private String program;

    private String duration;

    private String organization;
}