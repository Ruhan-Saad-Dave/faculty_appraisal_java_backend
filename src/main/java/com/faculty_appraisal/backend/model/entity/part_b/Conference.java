package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "conferences")
@Data
public class Conference extends BasePartBModel {

    private String title;

    private String type;

    private String organization;

    private String level;
}