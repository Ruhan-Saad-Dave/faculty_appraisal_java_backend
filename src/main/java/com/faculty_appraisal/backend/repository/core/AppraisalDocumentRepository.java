package com.faculty_appraisal.backend.repository.core;

import com.faculty_appraisal.backend.model.entity.core.AppraisalDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AppraisalDocumentRepository extends BaseAppraisalRepository<AppraisalDocument, UUID> {

    List<AppraisalDocument> findByFacultyEmail(String facultyEmail);

    List<AppraisalDocument> findByFacultyEmailAndAcademicYear(
            String facultyEmail,
            String academicYear
    );

    List<AppraisalDocument> findByFacultyEmailAndAcademicYearAndSection(
            String facultyEmail,
            String academicYear,
            String section
    );

    List<AppraisalDocument> findByAcademicYear(String academicYear);

    List<AppraisalDocument> findByDocKey(String docKey);

    @Modifying
    @Query("DELETE FROM AppraisalDocument d WHERE d.facultyEmail = :email AND d.academicYear = :year")
    void deleteByFacultyEmailAndAcademicYear(@Param("email") String email, @Param("year") String year);

}