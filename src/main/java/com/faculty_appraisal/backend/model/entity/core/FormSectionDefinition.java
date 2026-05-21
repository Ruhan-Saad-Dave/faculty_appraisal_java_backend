package com.faculty_appraisal.backend.model.entity.core;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "form_section_definitions")
@Data
public class FormSectionDefinition {

    @Id
    private String code;

    @Column(name = "form_family", nullable = false)
    private String formFamily;

    @Column(nullable = false)
    private String part;

    @Column(name = "section_key", nullable = false)
    private String sectionKey;

    @Column(nullable = false)
    private String title;

    @Column(name = "max_marks", nullable = false)
    private BigDecimal maxMarks;

    @Column(name = "storage_table")
    private String storageTable;

    @Column(columnDefinition = "jsonb", nullable = false)
    private String fields;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
