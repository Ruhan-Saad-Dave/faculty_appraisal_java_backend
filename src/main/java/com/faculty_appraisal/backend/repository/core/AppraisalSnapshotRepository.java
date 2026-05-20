package com.faculty_appraisal.backend.repository.core;

import com.faculty_appraisal.backend.model.entity.core.AppraisalSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppraisalSnapshotRepository extends JpaRepository<AppraisalSnapshot, UUID> {

    List<AppraisalSnapshot> findByFacultyEmail(String facultyEmail);

    List<AppraisalSnapshot> findByAcademicYear(String academicYear);

    List<AppraisalSnapshot> findByFacultyEmailAndAcademicYear(
            String facultyEmail,
            String academicYear
    );

    Optional<AppraisalSnapshot> findFirstByFacultyEmailAndAcademicYearOrderByCreatedAtDesc(
            String facultyEmail,
            String academicYear
    );
}