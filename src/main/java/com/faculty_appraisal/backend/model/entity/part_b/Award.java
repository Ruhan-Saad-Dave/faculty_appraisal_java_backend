package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "awards")
@Data
public class Award extends BasePartBModel {

    private String title;

    @Column(name = "award_date")
    private LocalDate awardDate;

    private String agency;

    private String level;
}