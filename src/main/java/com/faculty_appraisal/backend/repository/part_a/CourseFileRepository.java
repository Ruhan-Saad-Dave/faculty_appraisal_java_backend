package com.faculty_appraisal.backend.repository.part_a;

import com.faculty_appraisal.backend.model.entity.part_a.CourseFile;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseFileRepository extends BaseAppraisalRepository<CourseFile, UUID> {

    List<CourseFile> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    List<CourseFile> findByRowNo(Integer rowNo);

    Optional<CourseFile> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<CourseFile> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);

    void deleteByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);
}