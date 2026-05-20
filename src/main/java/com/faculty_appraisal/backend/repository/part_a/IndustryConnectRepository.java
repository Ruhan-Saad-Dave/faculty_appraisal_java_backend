package com.faculty_appraisal.backend.repository.part_a;

import com.faculty_appraisal.backend.model.entity.part_a.IndustryConnect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IndustryConnectRepository extends JpaRepository<IndustryConnect, UUID> {

    List<IndustryConnect> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    List<IndustryConnect> findByRowNo(Integer rowNo);

    Optional<IndustryConnect> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<IndustryConnect> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);
}