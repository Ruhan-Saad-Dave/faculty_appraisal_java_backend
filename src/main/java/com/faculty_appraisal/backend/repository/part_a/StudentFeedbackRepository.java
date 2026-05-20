package com.faculty_appraisal.backend.repository.part_a;

import com.faculty_appraisal.backend.model.entity.part_a.StudentFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentFeedbackRepository extends JpaRepository<StudentFeedback, UUID> {

    List<StudentFeedback> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    List<StudentFeedback> findByRowNo(Integer rowNo);

    Optional<StudentFeedback> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<StudentFeedback> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);

    Optional<StudentFeedback> findByFacultyEmailAndAcademicYearAndCourseCode(String facultyEmail, String academicYear, String courseCode);
}