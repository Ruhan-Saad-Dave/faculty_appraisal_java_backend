package com.faculty_appraisal.backend.repository.part_a;

import com.faculty_appraisal.backend.model.entity.part_a.ACRScore;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ACRScoreRepository extends BaseAppraisalRepository<ACRScore, UUID> {

    List<ACRScore> findByRowNo(Integer rowNo);

    Optional<ACRScore> findByRowNoAndLabel(Integer rowNo, String label);

    List<ACRScore> findAllByOrderByRowNoAsc();

    void deleteByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);
}