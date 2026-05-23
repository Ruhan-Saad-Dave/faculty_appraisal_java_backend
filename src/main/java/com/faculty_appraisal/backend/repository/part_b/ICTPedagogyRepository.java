package com.faculty_appraisal.backend.repository.part_b;

import com.faculty_appraisal.backend.model.entity.part_b.ICTPedagogy;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICTPedagogyRepository extends BaseAppraisalRepository<ICTPedagogy, UUID> {

    List<ICTPedagogy> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    Optional<ICTPedagogy> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<ICTPedagogy> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);

    void deleteByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);
}