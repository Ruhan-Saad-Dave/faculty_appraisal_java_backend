package com.faculty_appraisal.backend.repository.part_a;

import com.faculty_appraisal.backend.model.entity.part_a.ProjectGuided;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectGuidedRepository extends BaseAppraisalRepository<ProjectGuided, UUID> {

    List<ProjectGuided> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    List<ProjectGuided> findByRowNo(Integer rowNo);

    Optional<ProjectGuided> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<ProjectGuided> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);
}