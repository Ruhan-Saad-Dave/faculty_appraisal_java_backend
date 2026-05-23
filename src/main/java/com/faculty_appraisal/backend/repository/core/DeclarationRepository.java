package com.faculty_appraisal.backend.repository.core;

import com.faculty_appraisal.backend.model.entity.core.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeclarationRepository extends JpaRepository<Declaration, UUID> {

    List<Declaration> findByFacultyEmail(String facultyEmail);

    List<Declaration> findByAcademicYear(String academicYear);

    List<Declaration> findByStatus(String status);

    Optional<Declaration> findByFacultyEmailAndAcademicYear(
            String facultyEmail,
            String academicYear
    );

    List<Declaration> findByFacultyEmailAndStatus(
            String facultyEmail,
            String status
    );

    List<Declaration> findByFacultyEmailInAndAcademicYear(
            Collection<String> facultyEmails,
            String academicYear
    );
}