package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "research_proposals")
@Data
public class ResearchProposal extends BasePartBModel {

    private String title;

    private String duration;

    private String agency;

    private BigDecimal amount;
}