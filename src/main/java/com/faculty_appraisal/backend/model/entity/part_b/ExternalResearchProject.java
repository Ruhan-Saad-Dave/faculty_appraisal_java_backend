package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "external_research_projects")
@Data
public class ExternalResearchProject extends BasePartBModel {

    private String title;

    private String agency;

    @Column(name = "sanction_date")
    private LocalDate sanctionDate;

    private BigDecimal amount;

    private String role;

    @Column(name = "project_status")
    private String projectStatus;
}