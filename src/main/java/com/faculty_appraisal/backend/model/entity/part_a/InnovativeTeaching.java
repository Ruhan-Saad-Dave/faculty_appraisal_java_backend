package com.faculty_appraisal.backend.model.entity.part_a;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "innovative_teaching")
@Data
public class InnovativeTeaching extends BasePartAModel {

    private String details;
}