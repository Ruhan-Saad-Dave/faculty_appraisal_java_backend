package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "journal_publications")
@Data
public class JournalPublication extends BasePartBModel {

    private String title;

    private String journal;

    private String issn;

    private String indexing;
}