package com.faculty_appraisal.backend.repository.core;

import com.faculty_appraisal.backend.model.entity.core.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT DISTINCT d.academicYear FROM Declaration d ORDER BY d.academicYear DESC")
    List<String> findDistinctAcademicYearsOrderDesc();

    @Query("SELECT d.facultyEmail FROM Declaration d WHERE d.academicYear = :year")
    List<String> findFacultyEmailsByAcademicYear(@Param("year") String year);
}