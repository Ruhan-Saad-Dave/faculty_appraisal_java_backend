package com.faculty_appraisal.backend.repository.part_a;

import com.faculty_appraisal.backend.model.entity.part_a.InnovativeTeaching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface    InnovativeTeachingRepository extends JpaRepository<InnovativeTeaching, UUID> {

    Optional<InnovativeTeaching> findByFacultyEmailAndAcademicYear(
            String facultyEmail, String academicYear);

    Optional<InnovativeTeaching> findByFacultyEmailAndAcademicYearAndFormFamily(String facultyEmail, String academicYear, String formFamily);

    List<InnovativeTeaching> findAllByFacultyEmailAndAcademicYearOrderByCreatedAtDesc(String facultyEmail, String academicYear);
}