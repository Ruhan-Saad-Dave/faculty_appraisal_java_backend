package com.faculty_appraisal.backend.repository.part_a;

import com.faculty_appraisal.backend.model.entity.part_a.TeachingProcess;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeachingProcessRepository extends BaseAppraisalRepository<TeachingProcess, UUID> {

    List<TeachingProcess> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    List<TeachingProcess> findByRowNo(Integer rowNo);

    Optional<TeachingProcess> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<TeachingProcess> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);

    Optional<TeachingProcess> findByFacultyEmailAndAcademicYearAndCourseCodeAndSemester(
            String facultyEmail, String academicYear, String courseCode, String semester);
}