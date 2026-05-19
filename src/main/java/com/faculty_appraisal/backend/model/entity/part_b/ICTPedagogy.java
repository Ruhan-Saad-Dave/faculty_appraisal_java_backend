package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ict_pedagogy")
@Data
public class ICTPedagogy extends BasePartBModel {

    private String title;

    private String description;

    private String type;

    private String quadrant;
}