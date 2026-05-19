package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "patents")
@Data
public class Patent extends BasePartBModel {

    private String title;

    private String type;

    private String scope;

    @Column(name = "patent_date")
    private LocalDate patentDate;

    @Column(name = "patent_status")
    private String patentStatus;

    @Column(name = "file_no")
    private String fileNo;
}