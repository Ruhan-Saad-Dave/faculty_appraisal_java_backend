package com.faculty_appraisal.backend.repository.part_a;

import com.faculty_appraisal.backend.model.entity.part_a.SocialContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SocialContributionRepository extends JpaRepository<SocialContribution, UUID> {

    List<SocialContribution> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    List<SocialContribution> findByRowNo(Integer rowNo);

    Optional<SocialContribution> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<SocialContribution> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);
}