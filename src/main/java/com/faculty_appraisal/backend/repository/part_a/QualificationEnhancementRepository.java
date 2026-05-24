package com.faculty_appraisal.backend.repository.part_a;

import com.faculty_appraisal.backend.model.entity.part_a.QualificationEnhancement;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QualificationEnhancementRepository extends BaseAppraisalRepository<QualificationEnhancement, UUID> {

    List<QualificationEnhancement> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    List<QualificationEnhancement> findByRowNo(Integer rowNo);

    Optional<QualificationEnhancement> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<QualificationEnhancement> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);
}