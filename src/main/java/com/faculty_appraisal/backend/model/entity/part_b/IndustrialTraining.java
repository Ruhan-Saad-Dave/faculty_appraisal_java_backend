package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "industrial_training")
@Data
public class IndustrialTraining extends BasePartBModel {

    private String company;

    private String duration;

    private String nature;
}