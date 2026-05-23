package com.faculty_appraisal.backend.repository.non_teaching;

import com.faculty_appraisal.backend.model.entity.non_teaching.NTWorkflowInstanceStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NTWorkflowInstanceStepRepository extends JpaRepository<NTWorkflowInstanceStep, UUID> {

    List<NTWorkflowInstanceStep> findByInstanceIdOrderByStepNoAsc(UUID instanceId);
}
