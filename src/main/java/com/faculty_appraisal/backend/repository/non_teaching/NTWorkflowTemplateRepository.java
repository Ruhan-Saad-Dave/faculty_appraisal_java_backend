package com.faculty_appraisal.backend.repository.non_teaching;

import com.faculty_appraisal.backend.model.entity.non_teaching.NTWorkflowTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NTWorkflowTemplateRepository extends JpaRepository<NTWorkflowTemplate, UUID> {

    Optional<NTWorkflowTemplate> findFirstByIsDefaultTrueAndIsActiveTrue();

    List<NTWorkflowTemplate> findAllByOrderByIsDefaultDescNameAsc();
}
