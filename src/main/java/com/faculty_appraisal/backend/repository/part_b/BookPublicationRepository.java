package com.faculty_appraisal.backend.repository.part_b;

import com.faculty_appraisal.backend.model.entity.part_b.BookPublication;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookPublicationRepository extends BaseAppraisalRepository<BookPublication, UUID> {

    List<BookPublication> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    Optional<BookPublication> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<BookPublication> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);
}