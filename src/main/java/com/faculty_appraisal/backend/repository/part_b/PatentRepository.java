package com.faculty_appraisal.backend.repository.part_b;

import com.faculty_appraisal.backend.model.entity.part_b.Patent;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatentRepository extends BaseAppraisalRepository<Patent, UUID> {

    List<Patent> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    Optional<Patent> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<Patent> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);

    void deleteByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);
}