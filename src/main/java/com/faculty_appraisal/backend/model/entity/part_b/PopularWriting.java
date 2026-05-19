package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "popular_writings")
@Data
public class PopularWriting extends BasePartBModel {

    private String media;

    private String film;
}