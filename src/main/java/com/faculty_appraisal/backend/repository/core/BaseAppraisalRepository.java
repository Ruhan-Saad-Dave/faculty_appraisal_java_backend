package com.faculty_appraisal.backend.repository.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseAppraisalRepository<T, ID> extends JpaRepository<T, ID> {
    void deleteByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);
}
