package com.faculty_appraisal.backend.repository.part_b;

import com.faculty_appraisal.backend.model.entity.part_b.ResearchProject;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResearchProjectRepository extends BaseAppraisalRepository<ResearchProject, UUID> {

    List<ResearchProject> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    Optional<ResearchProject> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<ResearchProject> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);
}