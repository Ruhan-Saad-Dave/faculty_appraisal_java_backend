package com.faculty_appraisal.backend.repository.part_b;

import com.faculty_appraisal.backend.model.entity.part_b.JournalPublication;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JournalPublicationRepository extends BaseAppraisalRepository<JournalPublication, UUID> {

    List<JournalPublication> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    Optional<JournalPublication> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<JournalPublication> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);
}