package com.faculty_appraisal.backend.model.entity.part_a;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "industry_connect")
@Data
public class IndustryConnect extends BasePartAModel {

    private String name;

    private String details;
}