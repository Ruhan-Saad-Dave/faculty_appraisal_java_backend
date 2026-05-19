package com.faculty_appraisal.backend.model.entity.part_a;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "course_files")
@Data
public class CourseFile extends BasePartAModel {

    @Column(name = "row_no")
    private Integer rowNo;

    private String course;

    private String title;

    private String details;
}
