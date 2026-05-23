package com.faculty_appraisal.backend.repository.part_b;

import com.faculty_appraisal.backend.model.entity.part_b.IndustrialTraining;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IndustrialTrainingRepository extends BaseAppraisalRepository<IndustrialTraining, UUID> {

    List<IndustrialTraining> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    Optional<IndustrialTraining> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<IndustrialTraining> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);

    void deleteByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);
}