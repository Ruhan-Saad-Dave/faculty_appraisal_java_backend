package com.faculty_appraisal.backend.repository.non_teaching;

import com.faculty_appraisal.backend.model.entity.non_teaching.NTWorkflowTemplateStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NTWorkflowTemplateStepRepository extends JpaRepository<NTWorkflowTemplateStep, UUID> {

    List<NTWorkflowTemplateStep> findByTemplateIdOrderByStepNoAsc(UUID templateId);

    Optional<NTWorkflowTemplateStep> findByTemplateIdAndStepNo(UUID templateId, int stepNo);

    @Query("SELECT COUNT(s) FROM NTWorkflowTemplateStep s WHERE s.template.id = :templateId")
    long countByTemplateId(@Param("templateId") UUID templateId);

    @Query("SELECT MAX(s.stepNo) FROM NTWorkflowTemplateStep s WHERE s.template.id = :templateId")
    Optional<Integer> findMaxStepNoByTemplateId(@Param("templateId") UUID templateId);
}
