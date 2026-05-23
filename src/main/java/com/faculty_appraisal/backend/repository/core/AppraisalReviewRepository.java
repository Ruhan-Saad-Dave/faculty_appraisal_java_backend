package com.faculty_appraisal.backend.repository.core;

import com.faculty_appraisal.backend.model.entity.core.AppraisalReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppraisalReviewRepository extends JpaRepository<AppraisalReview, UUID> {

    List<AppraisalReview> findByFacultyEmail(String facultyEmail);

    List<AppraisalReview> findByAcademicYear(String academicYear);

    List<AppraisalReview> findByReviewerEmail(String reviewerEmail);

    List<AppraisalReview> findByReviewerRole(String reviewerRole);

    List<AppraisalReview> findByStatus(String status);

    List<AppraisalReview> findByFacultyEmailAndAcademicYear(
            String facultyEmail,
            String academicYear
    );

    Optional<AppraisalReview> findByFacultyEmailAndAcademicYearAndReviewerRole(
            String facultyEmail,
            String academicYear,
            String reviewerRole
    );

    List<AppraisalReview> findByFacultyEmailInAndAcademicYear(
            Collection<String> facultyEmails,
            String academicYear
    );
}