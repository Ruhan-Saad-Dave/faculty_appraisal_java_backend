package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "ipr_records")
@Data
public class IPRRecord extends BasePartBModel {

    private String title;

    private String scope;

    @Column(name = "ipr_date")
    private LocalDate iprDate;

    @Column(name = "ipr_status")
    private String iprStatus;

    @Column(name = "file_no")
    private String fileNo;
}