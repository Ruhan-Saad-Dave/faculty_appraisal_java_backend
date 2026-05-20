package com.faculty_appraisal.backend.repository.core;

import com.faculty_appraisal.backend.model.entity.core.AppraisalConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface AppraisalConfigRepository extends JpaRepository<AppraisalConfig, Integer> {

    Optional<AppraisalConfig> findByAcademicYear(String academicYear);

    List<AppraisalConfig> findByIsOpenTrue();

    Optional<AppraisalConfig> findFirstByIsOpenTrue();
}