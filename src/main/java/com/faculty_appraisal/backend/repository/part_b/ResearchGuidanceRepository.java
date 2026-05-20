package com.faculty_appraisal.backend.repository.part_b;

import com.faculty_appraisal.backend.model.entity.part_b.ResearchGuidance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResearchGuidanceRepository extends JpaRepository<ResearchGuidance, UUID> {

    List<ResearchGuidance> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    Optional<ResearchGuidance> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<ResearchGuidance> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);
}