package com.faculty_appraisal.backend.repository.part_a;

import com.faculty_appraisal.backend.model.entity.part_a.UniversityActivity;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UniversityActivityRepository extends BaseAppraisalRepository<UniversityActivity, UUID> {

    List<UniversityActivity> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    List<UniversityActivity> findByRowNo(Integer rowNo);

    Optional<UniversityActivity> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<UniversityActivity> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);

    void deleteByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);
}