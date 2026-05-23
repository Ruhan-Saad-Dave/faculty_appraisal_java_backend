package com.faculty_appraisal.backend.repository.core;

import com.faculty_appraisal.backend.model.entity.core.ReviewerSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ReviewerSnapshotRepository extends JpaRepository<ReviewerSnapshot, UUID> {

    Optional<ReviewerSnapshot> findByFacultyEmailAndAcademicYearAndReviewerRole(
            String facultyEmail, String academicYear, String reviewerRole);

    @Modifying
    @Query("DELETE FROM ReviewerSnapshot r WHERE r.facultyEmail = :email AND r.academicYear = :year AND r.reviewerRole = :role")
    void deleteByFacultyEmailAndAcademicYearAndReviewerRole(
            @Param("email") String email,
            @Param("year") String year,
            @Param("role") String role);
}
