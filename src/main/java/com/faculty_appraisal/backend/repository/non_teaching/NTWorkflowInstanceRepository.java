package com.faculty_appraisal.backend.repository.non_teaching;

import com.faculty_appraisal.backend.model.entity.non_teaching.NTWorkflowInstance;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NTWorkflowInstanceRepository extends JpaRepository<NTWorkflowInstance, UUID> {

    @EntityGraph(attributePaths = {"instanceSteps"})
    Optional<NTWorkflowInstance> findByStaffEmailAndAcademicYear(String staffEmail, String academicYear);
}
