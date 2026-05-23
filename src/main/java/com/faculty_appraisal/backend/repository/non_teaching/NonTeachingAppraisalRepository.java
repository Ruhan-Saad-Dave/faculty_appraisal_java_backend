package com.faculty_appraisal.backend.repository.non_teaching;

import com.faculty_appraisal.backend.model.entity.non_teaching.NonTeachingAppraisal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NonTeachingAppraisalRepository extends JpaRepository<NonTeachingAppraisal, UUID> {

    List<NonTeachingAppraisal> findByStaffEmail(String staffEmail);

    List<NonTeachingAppraisal> findByAcademicYear(String academicYear);

    List<NonTeachingAppraisal> findByStatus(String status);

    Optional<NonTeachingAppraisal> findByStaffEmailAndAcademicYear(
            String staffEmail,
            String academicYear
    );

    List<NonTeachingAppraisal> findByStaffEmailAndStatus(
            String staffEmail,
            String status
    );

    List<NonTeachingAppraisal> findByStaffEmailInAndAcademicYear(
            Collection<String> staffEmails,
            String academicYear
    );
}