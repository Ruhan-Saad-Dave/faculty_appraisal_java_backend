package com.faculty_appraisal.backend.repository.part_b;

import com.faculty_appraisal.backend.model.entity.part_b.SelfDevelopment;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;

import java.util.List;
import java.util.UUID;

public interface SelfDevelopmentRepository extends BaseAppraisalRepository<SelfDevelopment, UUID> {

    List<SelfDevelopment> findByFacultyEmail(String facultyEmail);

    List<SelfDevelopment> findByAcademicYear(String academicYear);

    List<SelfDevelopment> findByFacultyEmailAndAcademicYear(
            String facultyEmail,
            String academicYear
    );

    List<SelfDevelopment> findByProgram(String program);

    List<SelfDevelopment> findByOrganization(String organization);

    List<SelfDevelopment> findByFacultyEmailAndAcademicYearOrderByRowNo(
            String facultyEmail, String academicYear);

}